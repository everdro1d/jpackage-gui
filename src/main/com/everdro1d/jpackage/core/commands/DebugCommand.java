package main.com.everdro1d.jpackage.core.commands;

import com.everdro1d.libs.commands.CommandInterface;
import com.everdro1d.libs.commands.CommandManager;
import main.com.everdro1d.jpackage.core.MainWorker;

public class DebugCommand implements CommandInterface {
    @Override
    public void execute(CommandManager commandManager) {
        MainWorker.debug = true;
        System.out.println("Debug mode enabled.");
    }
}
