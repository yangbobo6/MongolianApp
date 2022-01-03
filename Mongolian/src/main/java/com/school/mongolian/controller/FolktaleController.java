package com.school.mongolian.controller;

import com.school.mongolian.dao.FolktaleDao;
import com.school.mongolian.dto.FolktaleDto;
import com.school.mongolian.po.FolkTale;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("folktale")
public class FolktaleController {
    private static final String prefix = "http://";

    @Autowired
    private FolktaleDao folktaleDao;

    @RequestMapping("/getAllFolktale")
    public Result<List<FolktaleDto>> getAllFolktale(@RequestParam("collect")int collect){



        //新建list存储dto
        List<FolktaleDto> folktaleDtos = new ArrayList<>();

        //获取数据库Folktale_list集合，需要改动list
        List<FolkTale> allFolktale = folktaleDao.getAllFolktale();
        if(allFolktale.size()==0){
            return Result.error(CodeMsg.FOLKTALE_ERROR);
        }

        for (FolkTale f:allFolktale
             ) {
            //新建dto对象
            FolktaleDto folktaleDto = new FolktaleDto();
            List<String> folktaleList = Arrays.asList(f.getFolktaleUrl().split(";"));
            for (String s:folktaleList
                 ) {
                s = prefix+s;
                //System.out.println(s);
            }
            for (int i = 0; i < folktaleList.size(); i++) {
                folktaleList.set(i,prefix+folktaleList.get(i));
            }
            folktaleDto.setId(f.getId());
            folktaleDto.setName(f.getName());
            folktaleDto.setUrlList(folktaleList);
            folktaleDto.setCollect(f.getCollect());
            folktaleDto.setType(f.getType());
            //System.out.println(folktaleDto);
            folktaleDtos.add(folktaleDto);
            //System.out.println(folktaleDtos );
        }
//        for (int i = 0; i < allFolktale.size(); i++) {
//
//            folktaleDto.setId(allFolktale.get(i).getId());
//            folktaleDto.setName(allFolktale.get(i).getName());
//            folktaleDto.setUrlList(Arrays.asList(allFolktale.get(i).getFolktaleUrl().split(";")));
//            folktaleDto.setCollect(allFolktale.get(i).getCollect());
//            folktaleDto.setType(allFolktale.get(i).getType());
//            folktaleDtos.add(folktaleDto);
//            System.out.println(folktaleDtos);
//            folktaleDtos.add(folktaleDto);
//        }
//        for (FolktaleDto fo:folktaleDtos
//             ) {
//            System.out.println(fo);
//        }
        if(collect==1){
            List<FolktaleDto> folktaleDtos1 = new ArrayList<>();
            for (FolktaleDto f:folktaleDtos
                 ) {
                if (f.getCollect()==1){
                    folktaleDtos1.add(f);
                }
            }
            return Result.success(folktaleDtos1);
        }

        return Result.success(folktaleDtos);

    }
}
