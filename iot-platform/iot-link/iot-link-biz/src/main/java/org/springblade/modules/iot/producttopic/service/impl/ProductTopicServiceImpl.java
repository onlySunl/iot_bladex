package org.springblade.modules.iot.producttopic.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotproducttopicserviceimplProductTopicServiceImpl.java.mapper.ProductTopicMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.product.enumeration.ProductTypeEnum;
import org.springblade.modules.iot.producttopic.config.ProductTopicTemplate;
import org.springblade.modules.iot.producttopic.config.ProductTopicTemplateConfig;
import org.springblade.modules.iot.producttopic.entity.ProductTopic;
import org.springblade.modules.iot.producttopic.enumeration.ProductTopicTypeEnum;
import org.springblade.modules.iot.producttopic.service.ProductTopicService;
import org.springblade.modules.iot.producttopic.vo.save.ProductTopicSaveVO;
import org.springblade.modules.iot.producttopic.vo.update.ProductTopicUpdateVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 涓氬姟瀹炵幇绫?
 * 浜у搧Topic淇℃伅琛?
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Service
public class ProductTopicServiceImpl extends BaseServiceImpl<ProductTopicMapper, ProductTopic> implements ProductTopicService {

    private final ProductTopicTemplateConfig topicTemplateConfig;

    @Override
    protected <UpdateVO> ProductTopic updateBefore(UpdateVO vo) {
        ProductTopicUpdateVO updateVO = (ProductTopicUpdateVO) vo;

        if (superManager.count(Wrappers.<ProductTopic>lbQ()
                .eq(ProductTopic::getProductIdentification, updateVO.getProductIdentification())
                .eq(ProductTopic::getTopic, updateVO.getTopic())
                .ne(ProductTopic::getId, updateVO.getId())) > 0) {
            throw BizException.wrap("Topic宸插瓨鍦?);
        }

        return super.updateBefore(updateVO);
    }

    @Override
    protected <SaveVO> ProductTopic saveBefore(SaveVO vo) {
        ProductTopicSaveVO saveVO = (ProductTopicSaveVO) vo;

        if (superManager.count(Wrappers.<ProductTopic>lbQ()
                .eq(ProductTopic::getProductIdentification, saveVO.getProductIdentification())
                .eq(ProductTopic::getTopic, saveVO.getTopic())) > 0) {
            throw BizException.wrap("Topic宸插瓨鍦?);
        }

        return super.saveBefore(saveVO);
    }

    @Override
    protected <SaveVO> void saveAfter(SaveVO saveVO, ProductTopic entity) {
//        superManager.refreshProductTopicCache(Collections.singletonList(entity));
    }

    @Override
    protected <UpdateVO> void updateAfter(UpdateVO updateVO, ProductTopic entity) {
//        superManager.refreshProductTopicCache(Collections.singletonList(entity));
    }

    /**
     * 鍒濆鍖栦骇鍝佸熀纭€Topic
     *
     * @param productIdentification 浜у搧鏍囪瘑
     * @param productTypeEnum       浜у搧绫诲瀷鏋氫妇
     * @param reInit                鏄惁閲嶆柊鍒濆鍖?
     */
    @Override
    public void initProductBaseTopics(String productIdentification, ProductTypeEnum productTypeEnum, Boolean reInit) {
        log.info("寮€濮嬪垵濮嬪寲浜у搧鍩虹Topic - 浜у搧鏍囪瘑: {}, 浜у搧绫诲瀷: {}, 鏄惁閲嶆柊鍒濆鍖? {}", productIdentification, productTypeEnum.getDesc(), reInit);
        List<ProductTopicTemplate> templates = getTopicTemplates(productTypeEnum);
        if (CollectionUtil.isEmpty(templates)) {
            log.warn("鏈壘鍒颁骇鍝佺被鍨媅{}]瀵瑰簲鐨勫熀纭€Topic妯℃澘", productTypeEnum.getDesc());
            return;
        }
        boolean alreadyInitialized = isAlreadyInitialized(productIdentification);
        if (alreadyInitialized) {
            if (Boolean.TRUE.equals(reInit)) {
                // 閲嶆柊鍒濆鍖栵細鍒犻櫎鐜版湁Topic鍚庨噸鏂板垱寤?
                log.info("浜у搧[{}]宸插瓨鍦ㄥ熀纭€Topic锛屾墽琛岄噸鏂板垵濮嬪寲", productIdentification);
                // 鍒犻櫎璇ヤ骇鍝佺殑鎵€鏈夊熀纭€Topic
                boolean deleteSuccess = deleteBaseTopicByProductIdentification(productIdentification);
                if (!deleteSuccess) {
                    throw BizException.wrap("鍒犻櫎鐜版湁鍩虹Topic澶辫触锛屾棤娉曢噸鏂板垵濮嬪寲");
                }
            } else {
                // 涓嶉噸鏂板垵濮嬪寲锛岀洿鎺ヨ烦杩?
                log.info("浜у搧[{}]鐨勫熀纭€Topic宸茬粡鍒濆鍖栬繃锛岃烦杩囧垵濮嬪寲", productIdentification);
                return;
            }
        }
        log.info("浜у搧[{}]娌℃湁鍩虹Topic锛屾墽琛岄娆″垵濮嬪寲", productIdentification);
        List<ProductTopic> productTopics = buildProductTopics(templates, productIdentification);
        superManager.saveBatch(productTopics);
        log.info("鎴愬姛涓轰骇鍝乕{}]鍒濆鍖栦簡{}涓熀纭€Topic", productIdentification, productTopics.size());

    }

    /** 鍗曟鏌ヨ鏈€澶?ID 鏁?闃叉鐢ㄦ埛浼犺秴澶у垪琛ㄦ拺鐖?SQL IN銆?*/
    private static final int MAX_IDS_PER_QUERY = 500;

    /**
     * 鎵归噺鏍规嵁 ID 鏌?topic 妯℃澘瀛楃涓层€俧ail-soft:浠讳綍寮傚父璺緞閮借繑鍥炵┖鍒楄〃銆?
     *
     * @author mqttsnet
     * @since 2026-05-06
     */
    @Override
    public List<String> findTopicsByIds(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        // 杩囨护 null 鍏冪礌 + 鍘婚噸 + 鎴埌涓婇檺,闃插尽鑴忓叆鍙?/ SQL IN 鐖嗛噺
        List<Long> safeIds = ids.stream()
                .filter(Objects::nonNull)
                .distinct()
                .limit(MAX_IDS_PER_QUERY)
                .collect(Collectors.toList());
        if (safeIds.isEmpty()) {
            return Collections.emptyList();
        }
        return Optional.ofNullable(superManager.listByIds(safeIds))
                .orElse(Collections.emptyList())
                .stream()
                .map(ProductTopic::getTopic)
                .filter(StrUtil::isNotBlank)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 鏍规嵁浜у搧鏍囪瘑鍒犻櫎鎵€鏈夊熀纭€Topic
     *
     * @param productIdentification 浜у搧鏍囪瘑
     * @return 鍒犻櫎鍩虹Topic缁撴灉
     */
    private boolean deleteBaseTopicByProductIdentification(String productIdentification) {
        log.info("寮€濮嬪垹闄や骇鍝佹墍鏈塗opic - 浜у搧鏍囪瘑: {}", productIdentification);
        // 鏌ヨ璇ヤ骇鍝佺殑鎵€鏈夊熀纭€Topic
        List<ProductTopic> existingTopics = superManager.list(Wrappers.<ProductTopic>lbQ()
                .eq(ProductTopic::getProductIdentification, productIdentification)
                .eq(ProductTopic::getTopicType, ProductTopicTypeEnum.BASIC.getValue()));

        if (CollectionUtil.isEmpty(existingTopics)) {
            log.info("浜у搧[{}]娌℃湁鍩虹Topic璁板綍锛屾棤闇€鍒犻櫎", productIdentification);
            return true;
        }

        // 鎵归噺鍒犻櫎
        List<Long> topicIds = existingTopics.stream()
                .map(ProductTopic::getId)
                .distinct()
                .collect(Collectors.toList());
        boolean deleteSuccess = superManager.removeByIds(topicIds);
        if (deleteSuccess) {
            log.info("鎴愬姛鍒犻櫎浜у搧[{}]鐨剓}涓熀纭€Topic", productIdentification, existingTopics.size());
        } else {
            log.error("鍒犻櫎浜у搧[{}]鍩虹Topic澶辫触", productIdentification);
            throw BizException.wrap("鍒犻櫎浜у搧鍩虹Topic澶辫触");
        }
        return deleteSuccess;
    }

    /**
     * 鑾峰彇Topic妯℃澘
     *
     * @param productTypeEnum 浜у搧绫诲瀷鏋氫妇
     * @return Topic妯℃澘鍒楄〃
     */
    private List<ProductTopicTemplate> getTopicTemplates(ProductTypeEnum productTypeEnum) {
        Map<String, List<ProductTopicTemplate>> templates = topicTemplateConfig.getProductTopicTemplates();
        if (CollectionUtil.isEmpty(templates)) {
            throw BizException.wrap("Nacos閰嶇疆涓湭鎵惧埌Topic妯℃澘閰嶇疆");
        }

        // 鏍规嵁浜у搧绫诲瀷鑾峰彇瀵瑰簲鐨勬ā鏉?
        String typeKey = getProductTypeKey(productTypeEnum);
        List<ProductTopicTemplate> templateList = templates.get(typeKey);
        if (CollectionUtil.isEmpty(templateList)) {
            throw BizException.wrap("涓嶆敮鎸佺殑浜у搧绫诲瀷: " + productTypeEnum.getDesc());
        }

        return templateList;
    }

    /**
     * 鑾峰彇浜у搧绫诲瀷瀵瑰簲鐨勯厤缃敭
     *
     * @param productTypeEnum 浜у搧绫诲瀷鏋氫妇
     * @return 閰嶇疆閿?
     */
    private String getProductTypeKey(ProductTypeEnum productTypeEnum) {
        switch (productTypeEnum) {
            case COMMON:
                return "COMMON";
            case GATEWAY:
                return "GATEWAY";
            default:
                return "COMMON";
        }
    }

    /**
     * 妫€鏌ユ槸鍚﹀凡缁忓垵濮嬪寲杩?
     * 浠呮鏌ュ熀纭€Topic鏄惁瀛樺湪
     *
     * @param productIdentification 浜у搧鏍囪瘑
     * @return {@link Boolean} 鏄惁宸插垵濮嬪寲
     */
    private boolean isAlreadyInitialized(String productIdentification) {
        Long count = superManager.lambdaQuery()
                .eq(ProductTopic::getProductIdentification, productIdentification)
                .eq(ProductTopic::getTopicType, ProductTopicTypeEnum.BASIC.getValue())
                .count();
        return count > 0;
    }

    /**
     * 鏋勫缓浜у搧Topic鍒楄〃
     *
     * @param templates             Topic妯℃澘鍒楄〃
     * @param productIdentification 浜у搧鏍囪瘑
     * @return {@link List<ProductTopic>} ProductTopic鍒楄〃
     */
    private List<ProductTopic> buildProductTopics(List<ProductTopicTemplate> templates, String productIdentification) {
        return templates.stream()
                .map(template -> buildProductTopic(template, productIdentification))
                .collect(Collectors.toList());
    }

    /**
     * 鏋勫缓鍗曚釜ProductTopic瀹炰綋
     *
     * @param template              Topic妯℃澘
     * @param productIdentification 浜у搧鏍囪瘑
     * @return {@link ProductTopic} ProductTopic瀹炰綋
     */
    private ProductTopic buildProductTopic(ProductTopicTemplate template,
                                           String productIdentification) {
        ProductTopic productTopic = new ProductTopic();
        productTopic.setProductIdentification(productIdentification);
        productTopic.setTopic(template.getTopic());
        productTopic.setPublisher(template.getPublisher());
        productTopic.setSubscriber(template.getSubscriber());
        productTopic.setFunctionType(template.getFunctionType());
        productTopic.setRemark(template.getRemark());
        productTopic.setTopicType(template.getTopicType());
        productTopic.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        return productTopic;
    }

}

