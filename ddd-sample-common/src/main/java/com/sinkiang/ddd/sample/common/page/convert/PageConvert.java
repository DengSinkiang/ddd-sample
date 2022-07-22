package com.sinkiang.ddd.sample.common.page.convert;

import com.sinkiang.ddd.sample.common.page.PageResult;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author dengxj
 * @date 2022/7/21 10:00
 */
public class PageConvert {

    public PageConvert() {
    }

    public static <T, R> PageResult<R> convert(long total, long current, long pageSize, List<T> data, Function<T, R> func) {
        PageResult<R> resultModel = new PageResult<>();
        resultModel.setTotal(total);
        resultModel.setPageSize(pageSize);
        resultModel.setCurrent(current);
        resultModel.setList(data.stream().map(func).collect(Collectors.toList()));
        return resultModel;
    }

    public static <R> PageResult<R> convert(long total, long current, long pageSize, List<R> data) {
        return convert(total, current, pageSize, data, (t) -> t);
    }
}
