package com.zzw.configuration;

import com.zzw.vo.UserVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zzw
 * @see
 * @since 2018/2/11
 */
@Configuration
@Import({UserVo.class})
public class ImportConfiguration {
}
