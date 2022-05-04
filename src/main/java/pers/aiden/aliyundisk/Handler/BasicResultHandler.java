package pers.aiden.aliyundisk.Handler;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.ResultHandler;
import org.springframework.shell.table.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pers.aiden.aliyundisk.Handler.mapping.DataMappingFactory;
import pers.aiden.aliyundisk.Handler.mapping.DataMappingHandler;
import pers.aiden.aliyundisk.module.ShellResult;
import pers.aiden.aliyundisk.module.ToStringModel;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: 范淼
 * @Date: 2022-05-01
 * @Description:
 */
@Component
public class BasicResultHandler implements ResultHandler<ShellResult> {

    @Autowired
    @Lazy
    private Terminal terminal;

    @Autowired
    private DataMappingFactory dataMappingFactory;

    @Override
    public void handleResult(ShellResult shellResult) {
        Table summaryTable = handle(shellResult,false);
        Table dataTable = handle(shellResult,true);
        print(summaryTable,"Result Summary");
        print(dataTable,"Result Detail");
    }

    private void print(Table table, String msg) {
        String render = table.render(terminal.getWidth());
        terminal.writer().println(msg);
        terminal.writer().println(render);
    }


    private Table handle(ShellResult shellResult,boolean isDetail) {
        TableModel tableModel = null;
        if (isDetail) {
            Object data = shellResult.getData();
            if (data instanceof List && !CollectionUtils.isEmpty((Collection<?>) data)) {
                Class<?> aClass = ((Collection) data).iterator().next().getClass();
                LinkedHashMap<String, Object> headers = dataMappingFactory.getDataMappingHandler(aClass).getHeaders();
                tableModel = new BeanListTableModel<>((List) data, headers);
            } else if (data instanceof ToStringModel) {
                LinkedHashMap<String, Object> headers = dataMappingFactory.getDataMappingHandler(data.getClass()).getHeaders();
                tableModel = new BeanListTableModel<>(Arrays.asList(data), headers);
            } else {
                LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
                headers.put("data","data");
                tableModel = new BeanListTableModel<>(Arrays.asList(shellResult), headers);
            }
        } else {
            LinkedHashMap<String, Object> headers = dataMappingFactory.getDataMappingHandler(shellResult.getClass()).getHeaders();
            headers.remove("data");
            tableModel = new BeanListTableModel<>(Arrays.asList(shellResult),headers);
        }
        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.table()).addWrapper(new DelimiterTextWrapper('\n'));
        tableBuilder.on(CellMatchers.table()).addSizer(new NoWrapSizeConstraints());
        tableBuilder.on(CellMatchers.ofType(Date.class)).addFormatter((date) -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new String[]{simpleDateFormat.format(date)};
        });
        Table table = tableBuilder.addHeaderBorder(BorderStyle.fancy_heavy)
                .addFullBorder(BorderStyle.fancy_light).build();
        return table;
    }
}
