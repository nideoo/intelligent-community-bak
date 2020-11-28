package com.enc.service.system.impl;

import com.enc.dao.mapper.system.AreaGroupMapper;
import com.enc.domain.platform.vo.areaGroup.AreaGroupListVo;
import com.enc.domain.platform.vo.areaGroup.AreaGroupUpdateVo;
import com.enc.service.system.AreaGroupService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-14 11:03
 */
@Service
public class AreaGroupServiceImpl implements AreaGroupService {

    @Value("${patch.url}")
    private String url;

    @Value("${patch.areaGroup.data-url}")
    private String areaGroupDataUrl;

    @Autowired
    AreaGroupMapper areaGroupMapper;

    @Override
    public PageInfo getAreaGroupList(AreaGroupListVo vo) {
        PageHelper.startPage(vo.getPageIndex(),vo.getPageSize());
        List<Map<String,Object>> list = areaGroupMapper.getAreaGroupListSql(vo);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void areaGroupUpdate(AreaGroupUpdateVo vo) {
        areaGroupMapper.areaGroupUpdateSql(vo);
    }

    @Override
    public String pictureHandle(MultipartFile file4, HttpServletRequest request) {
        String dataUrl = areaGroupDataUrl+file4.getOriginalFilename();
        String filePathOnServer = url+areaGroupDataUrl+file4.getOriginalFilename();
        File out = new File(filePathOnServer);
        try {
            FileUtils.copyInputStreamToFile(file4.getInputStream(),out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataUrl;
    }
}


















