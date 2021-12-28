package com.school.mongolian.controller.wechatController;

import com.github.pagehelper.PageInfo;
import com.school.mongolian.dao.wechatDao.BrandDao;
import com.school.mongolian.po.wechat.Brand;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import com.school.mongolian.service.impl.BrandServiceImpl;
import com.school.mongolian.service.impl.PageHelperServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "搜索商品")
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandServiceImpl brandService;

    @Autowired
    PageHelperServiceImpl pageHelperService;

    @RequestMapping("/brandList")
    public Result<List<Brand>> findAllBrand(){
        List<Brand> brandList = brandService.findAll();
        if(brandList.size()==0){
            return Result.error(CodeMsg.OutOfSchool);
        }
        return Result.success(brandList);
    }
    @ApiOperation("/brandSearch")
    @RequestMapping("/brandSearch")
    public Result<List<Brand>> searchBrand(@RequestBody Brand brand){
        List<Brand> brandSearchList = brandService.search(brand);
        if(brandSearchList.size()==0){
            Result.error(CodeMsg.OutOfSchool);
        }
        return Result.success(brandSearchList);
    }

    @RequestMapping("/pageHelp/{currentPage}/{pageSize}")
    public Result<PageInfo<Brand>> selectPageInfo(@PathVariable(value = "currentPage")Integer currentPage,
                                                  @PathVariable(value = "pageSize")Integer pageSize){
        PageInfo<Brand> pageInfo = pageHelperService.findAllByPage(currentPage, pageSize);
        return Result.success(pageInfo);
    }
}
