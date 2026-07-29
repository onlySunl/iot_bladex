package org.springblade.modules.iot.areatest;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.baidu.fsg.uid.UidGenerator;
import org.springblade.modules.iot.common.constant.DefValConstants;
import org.springblade.modules.iot.system.entity.system.DefArea;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.mqttsnet.basic.utils.TreeUtil.getTreePath;
import static org.springblade.modules.iot.common.constant.DefValConstants.TREE_PATH_SPLIT;

/**
 * 将国家统计局的数据封装成list
 *
 * @author mqttsnet
 * @date 2020年05月08日15:09:15
 */
@Component
@Slf4j
public class CityParserImpl implements CityParser {
    private static final String COMMON_URL = "http://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2023/";
    private static final String PROVINCE = "20";
    private static final String CITY = "30";
    private static final String COUNTY = "40";
    private static final String TOWNTR = "50";
    private static final String VLILAGERT = "60";
    private static final String SOURCE = "10";
    private static final Charset CHARSET = CharsetUtil.CHARSET_UTF_8;
    //    private static final Charset CHARSET = CharsetUtil.CHARSET_UTF_8;
    @Autowired
    private UidGenerator uidGenerator;


    @Override
    public List<DefArea> parseProvinces(int level) {
        if (level < 0) {
            return Collections.emptyList();
        }
        return this.parseProvince(CityParserImpl.COMMON_URL + "index.html", level);
    }

    private List<DefArea> parseProvince(String url, int level) {

        HttpRequest get = HttpUtil.createGet(url);
        HttpResponse response = get.execute();
        int statusCode = response.getStatus();

        // 处理301重定向
        if (statusCode == 301 || statusCode == 302) {
            String newUrl = response.header("Location");
            get = HttpUtil.createGet(newUrl);
            response = get.execute();
        }

        byte[] bytes = response.bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CityParserImpl.CHARSET, false);
        Document document = Jsoup.parse(htmlStr);
        // 获取 class='provincetr' 的元素
        Elements elements = document.getElementsByClass("provincetr");
        List<DefArea> list = new LinkedList<>();
        for (Element element : elements) {
            // 获取 elements 下属性是 href 的元素
            Elements links = element.getElementsByAttribute("href");
            list.addAll(links.parallelStream().map(link -> {
                String name = link.text();
                String href = link.attr("href");

                String code = href.substring(0, 2);
                int sort = links.indexOf(link);
                DefArea area = DefArea.builder()
                        .id(this.uidGenerator.getUid())
                        .code(code + "0000000000").name(name)
                        .source(CityParserImpl.SOURCE).sortValue(sort + 1)
                        .level(CityParserImpl.PROVINCE).fullName(name)
                        .treeGrade(0).treePath(TREE_PATH_SPLIT)
                        .parentId(DefValConstants.PARENT_ID)
                        .build();
                if (level > 0) {
                    area.setChildren(this.parseCity(area, CityParserImpl.COMMON_URL + href, level));
                }

                CityParserImpl.log.debug("省级数据:  {}  ", area);
                return area;
            }).filter(Objects::nonNull).collect(Collectors.toList()));
        }
        return list;
    }

    private List<DefArea> parseCity(DefArea parent, String url, int level) {
        String parentName = parent.getFullName();
        Long parentId = parent.getId();
        String parentTreePath = parent.getTreePath();
        HttpRequest get = HttpUtil.createGet(url);
        HttpResponse response = get.execute();
        int statusCode = response.getStatus();

        // 处理301重定向
        if (statusCode == 301 || statusCode == 302) {
            String newUrl = response.header("Location");
            get = HttpUtil.createGet(newUrl);
            response = get.execute();
        }

        byte[] bytes = response.bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CityParserImpl.CHARSET, false);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("citytr");

        return trs.parallelStream().map(tr -> {
            Elements links = tr.getElementsByTag("a");
            String href = links.get(0).attr("href");
            String code = links.get(0).text();
//            String cityCode = links.get(0).text().substring(0, 4);
            String name = links.get(1).text();
            int sort = trs.indexOf(tr);
            DefArea area = DefArea.builder()
                    .id(this.uidGenerator.getUid())
                    .code(code).name(name).source(CityParserImpl.SOURCE).sortValue(sort + 1)
                    .level(CityParserImpl.CITY).fullName(parentName + name)
                    .treeGrade(1).treePath(getTreePath(parentTreePath, parentId))
                    .parentId(parentId)
                    .build();
            CityParserImpl.log.debug("	市级数据:  {}  ", area);
            if (level > 1) {
                area.setChildren(this.parseCounty(area, CityParserImpl.COMMON_URL + href, level));
            }
            return area;
        }).filter(Objects::nonNull).collect(Collectors.toList());

    }


    private List<DefArea> parseCounty(DefArea parent, String url, int level) {
        String parentName = parent.getFullName();
        Long parentId = parent.getId();
        String parentTreePath = parent.getTreePath();
        HttpRequest get = HttpUtil.createGet(url);
        HttpResponse response = get.execute();
        int statusCode = response.getStatus();

        // 处理301重定向
        if (statusCode == 301 || statusCode == 302) {
            String newUrl = response.header("Location");
            get = HttpUtil.createGet(newUrl);
            response = get.execute();
        }

        byte[] bytes = response.bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CityParserImpl.CHARSET, false);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("countytr");

        if (trs.isEmpty()) {
            return this.parseTowntr(parent, url, level, 2);
        }
        return trs.parallelStream().map(tr -> {
            Elements links = tr.getElementsByTag("a");
            if (links.size() != 2) {
                return null;
            }
            String href = links.get(0).attr("href");
            String code = links.get(0).text();
            String name = links.get(1).text();
            int sort = trs.indexOf(tr);
            DefArea area = DefArea.builder()
                    .id(this.uidGenerator.getUid())
                    .code(code).name(name).source(CityParserImpl.SOURCE).sortValue(sort + 1)
                    .level(CityParserImpl.COUNTY).fullName(parentName + name)
                    .treeGrade(2).treePath(getTreePath(parentTreePath, parentId))
                    .parentId(parentId)
                    .build();

            CityParserImpl.log.debug("		县级数据:  {}  ", area);
            if (level > 2) {
                area.setChildren(this.parseTowntr(area, CityParserImpl.COMMON_URL + href.substring(2, 5) + "/" + href, level, 3));
            }
            return area;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 乡镇级数据
     */
    public List<DefArea> parseTowntr(DefArea parent, String url, int level, int treeGrade) {
        String parentName = parent.getFullName();
        Long parentId = parent.getId();
        String parentTreePath = parent.getTreePath();
        HttpRequest get = HttpUtil.createGet(url);
        HttpResponse response = get.execute();
        int statusCode = response.getStatus();

        // 处理301重定向
        if (statusCode == 301 || statusCode == 302) {
            String newUrl = response.header("Location");
            get = HttpUtil.createGet(newUrl);
            response = get.execute();
        }

        byte[] bytes = response.bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CityParserImpl.CHARSET, false);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("towntr");
        return trs.parallelStream().map(tr -> {
            Elements links = tr.getElementsByTag("a");
            if (links.size() != 2) {
                return null;
            }
            String href = links.get(0).attr("href");
            String code = links.get(0).text();
            String name = links.get(1).text();
            int sort = trs.indexOf(tr);
            DefArea area = DefArea.builder()
                    .id(this.uidGenerator.getUid())
                    .code(code).name(name).source(CityParserImpl.SOURCE).sortValue(sort + 1)
                    .level(CityParserImpl.TOWNTR).fullName(parentName + name)
                    .treeGrade(treeGrade).treePath(getTreePath(parentTreePath, parentId))
                    .parentId(parentId)
                    .build();
            CityParserImpl.log.debug("			乡镇级数据:  {}  ", area);
            if (level > 3) {
                area.setChildren(this.parseVillagetr(area,
                        CityParserImpl.COMMON_URL + href.substring(2, 5) + "/" + href.substring(5, 7) + "/" + href, treeGrade + 1));
            }

            return area;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 村庄数据
     */
    public List<DefArea> parseVillagetr(DefArea parent, String url, int treeGrade) {
        String parentName = parent.getFullName();
        Long parentId = parent.getId();
        String parentTreePath = parent.getTreePath();
        HttpRequest get = HttpUtil.createGet(url);
        HttpResponse response = get.execute();
        int statusCode = response.getStatus();

        // 处理301重定向
        if (statusCode == 301 || statusCode == 302) {
            String newUrl = response.header("Location");
            get = HttpUtil.createGet(newUrl);
            response = get.execute();
        }

        byte[] bytes = response.bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CityParserImpl.CHARSET, false);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("villagetr");
        return trs.parallelStream().map(tr -> {
            Elements tds = tr.getElementsByTag("td");
            if (tds.size() != 3) {
                return null;
            }
            String code = tds.get(0).text();
            String divisionCode = tds.get(1).text();
            String name = tds.get(2).text();
            int sort = trs.indexOf(tr);
            DefArea area = DefArea.builder()
                    .id(this.uidGenerator.getUid()).divisionCode(divisionCode)
                    .code(code).name(name).source(CityParserImpl.SOURCE).sortValue(sort + 1)
                    .level(CityParserImpl.VLILAGERT).fullName(parentName + name)
                    .treeGrade(treeGrade).treePath(getTreePath(parentTreePath, parentId))
                    .parentId(parentId)
                    .build();

            CityParserImpl.log.debug("				村级数据:  {}  ", area);

            return area;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

}
