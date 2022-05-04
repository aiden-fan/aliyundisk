package pers.aiden.aliyundisk.command;

import org.springframework.shell.ExitRequest;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;
import pers.aiden.aliyundisk.utils.ThreadPoolUtil;

@ShellComponent
public class IndexCommand implements Quit.Command{


    @ShellMethod("index")
    public String index() {

        return "welcome !!!";
    }

    @ShellMethod(
            value = "任务结束后关闭",
            key = {"quit", "exit"}
    )
    public void quit() {
        ThreadPoolUtil.threadPoolExecutor.shutdown();
        throw new ExitRequest();
    }


}
