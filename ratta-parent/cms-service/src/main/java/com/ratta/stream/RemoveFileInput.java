package com.ratta.stream;

import com.ratta.domain.*;
import com.ratta.dto.OssDTO;
import com.ratta.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@EnableBinding(CmsChannel.class)
@Service
@Slf4j
public class RemoveFileInput {

    @Autowired
    private OssDeleteOutput ossDeleteOutput;

    @Value("${bucketName}")
    private String bucketName;

    @Autowired
    private UploadRecordMapper uploadRecordMapper;

    @Autowired
    private AboutMapper aboutMapper;

    @Autowired
    private BlogMapper bolgMapper;

    @Autowired
    private FirmwareManualMapper firmwareManualMapper;

    @Autowired
    private HomeAwardMapper homeAwardMapper;

    @Autowired
    private HomeBannerMapper homeBannerMapper;

    @Autowired
    private PartMapper partMapper;

    @Autowired
    private PartSeriesMapper partSeriesMapper;

    @Autowired
    private ProductBannerMapper productBannerMapper;

    @Autowired
    private ProductDescriptionMapper productDescriptionMapper;

    @StreamListener(value = CmsChannel.CMS_INPUT, condition = "headers['file']=='delete'")
    public void delete(@Payload String s) {

        List<UploadRecordDO> uploadRecordDOList = uploadRecordMapper.query();

        // 查询t_about的所有数据
        List<AboutDO> aboutDOList = aboutMapper.select(null, null, null);
        // 查询t_blog的所有数据
        List<BlogDO> blogDOList = bolgMapper.query();
        // 查询t_firmware_manual的所有数据
        List<FirmwareManualDO> firmwareManualDOList = firmwareManualMapper.query(new FirmwareManualDO());
        // 查询t_home_award的所有数据
        List<HomeAwardDO> homeAwardDOList = homeAwardMapper.query();
        // 查询t_home_banner的所有数据
        List<HomeBannerDO> homeBannerDOList = homeBannerMapper.query();
        // 查询t_part的所有数据
        List<PartDO> partDOList = partMapper.query(new PartDO());
        // 查询t_part_series的所有数据
        List<PartSeriesDO> partSeriesDOList = partSeriesMapper.query(new PartSeriesDO());
        // 查询t_product_banner的所有数据
        List<ProductBannerDO> productBannerDOList = productBannerMapper.query();
        // 查询t_product_description
        List<ProductDescriptionDO> productDescriptionDOList = productDescriptionMapper.selectProductDescription(null,
                null, null, null);
        // 要删除的文件集合
        List<String> urlList = new ArrayList<String>();
        // 遍历已上传的文件
        for (UploadRecordDO uploadRecordDO : uploadRecordDOList) {
            // 代表不存在于表里
            boolean flag = false;
            for (AboutDO aboutDO : aboutDOList) {
                if (aboutDO.getDescriptionMobileCn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (aboutDO.getDescriptionPcCn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (aboutDO.getDescriptionMobileEn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (aboutDO.getDescriptionPcEn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
            }
            for (BlogDO blogDO : blogDOList) {
                if (blogDO.getContent().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (blogDO.getUrlPc().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (blogDO.getUrlMobile().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
            }

            for (FirmwareManualDO firmwareManualDO : firmwareManualDOList) {
                if (firmwareManualDO.getUrlCn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (!StringUtils.isBlank(firmwareManualDO.getUrlEn())) {
                    if (firmwareManualDO.getUrlEn().contains(uploadRecordDO.getInnerName())) {
                        flag = true;
                    }
                }
            }
            for (HomeAwardDO homeAwardDO : homeAwardDOList) {
                if (homeAwardDO.getUrlMobileCn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (homeAwardDO.getUrlPcCn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (homeAwardDO.getUrlMobileEn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (homeAwardDO.getUrlPcEn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
            }
            for (HomeBannerDO homeBannerDO : homeBannerDOList) {
                if (homeBannerDO.getUrlMobile().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (homeBannerDO.getUrlPc().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
            }
            for (PartDO partDO : partDOList) {
                if (partDO.getColourUrl().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (partDO.getProductUrl().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (partDO.getMatchUrl().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (!StringUtils.isBlank(partDO.getHomeUrl())) {
                    if (partDO.getHomeUrl().contains(uploadRecordDO.getInnerName())) {
                        flag = true;
                    }
                }
            }
            for (PartSeriesDO partSeriesDO : partSeriesDOList) {
                if (partSeriesDO.getMutiUrl().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (partSeriesDO.getContentMobileCn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (partSeriesDO.getContentPcCn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (partSeriesDO.getContentMobileEn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (partSeriesDO.getContentPcEn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
            }
            for (ProductBannerDO productBannerDO : productBannerDOList) {
                if (productBannerDO.getUrlMobile().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (productBannerDO.getUrlPc().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
            }
            for (ProductDescriptionDO ProductDescriptionDO : productDescriptionDOList) {
                if (ProductDescriptionDO.getDescriptionMobileCn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (ProductDescriptionDO.getDescriptionPcCn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (ProductDescriptionDO.getDescriptionMobileEn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }
                if (ProductDescriptionDO.getDescriptionPcEn().contains(uploadRecordDO.getInnerName())) {
                    flag = true;
                }

            }

            if (flag == false) {
                urlList.add(uploadRecordDO.getInnerName());
            }

        }

        log.info("urlList:{}", urlList);
        // 遍历要删除的数组，删除该ufile上的文件
        for (String innerName : urlList) {
            uploadRecordMapper.delete(innerName);
            ossDeleteOutput.delete(new OssDTO(bucketName, innerName));
        }
    }
}
