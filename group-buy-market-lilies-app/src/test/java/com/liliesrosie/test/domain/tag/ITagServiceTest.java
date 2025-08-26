package com.liliesrosie.test.domain.tag;

import com.liliesrosie.domain.tag.service.ITagService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author lingwenxu
 * @description
 * @create 2025-08-14 15:13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ITagServiceTest {

    @Resource
    ITagService tagService;

    @Test
    public void test_execTagBatchJob(){

        tagService.execTagBatchJob("RQ_KJHKL98UU78H66554GFDV", "10001");
    }
}
