

package org.springblade.modules.iot.persistence.page;

import org.springblade.modules.iot.common.utils.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;

/** 分页工具类 */
public class PageUtils extends PageHelper {

  /** 设置请求分页数据 */
  public static void startPage() {
    PageDomain pageDomain = TableSupport.buildPageRequest();
    Integer pageNum = pageDomain.getPageNum();
    Integer pageSize = pageDomain.getPageSize();
    if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
      String orderBy = pageDomain.getOrderBy();
      startPage(pageNum, pageSize, orderBy);
    }
  }

  /** 封装分页数据 */
  public static <T> TableDataInfo<T> getDataTable(List<T> list) {
    TableDataInfo<T> rspData = new TableDataInfo<>();
    rspData.setCode(0);
    rspData.setRows(list);
    rspData.setMsg("查询成功");
    if (list instanceof Page) {
      rspData.setTotal((int) ((Page<?>) list).getTotal());
    } else {
      rspData.setTotal(list.size());
    }
    return rspData;
  }
}
