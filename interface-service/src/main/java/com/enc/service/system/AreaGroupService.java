package com.enc.service.system;

import com.enc.domain.platform.vo.areaGroup.AreaGroupListVo;
import com.enc.domain.platform.vo.areaGroup.AreaGroupUpdateVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface AreaGroupService {

    PageInfo getAreaGroupList(AreaGroupListVo vo);

    void areaGroupUpdate(AreaGroupUpdateVo vo);

    String pictureHandle(@RequestParam("file4") MultipartFile file4, HttpServletRequest request);
}
