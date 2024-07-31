package org.example.aop.converter;

import org.example.aop.log.OperateLog;

/**
 * @author yoyocraft
 * @date 2024/08/01
 */
public interface OperateLogConverter<PARAM> {

    OperateLog convert(PARAM param);
}
