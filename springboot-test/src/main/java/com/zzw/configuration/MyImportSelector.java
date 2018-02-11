package com.zzw.configuration;

import com.zzw.vo.UserVo;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author zzw
 * @see
 * @since 2018/2/11
 */
@Component
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{UserVo.class.getName()};
    }
}
