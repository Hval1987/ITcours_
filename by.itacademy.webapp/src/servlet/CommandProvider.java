package servlet;

import impl.GoToReg;
import impl.GoToSignIn;
import impl.UserRegistration;
import impl.UserSignIn;
import servlet.Command;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String,Command> commandMap=new HashMap<String, Command>();

    public CommandProvider() {

        commandMap.put("GO_TO_REGISTRATION",new GoToReg());
        commandMap.put("DATA_FOR_REGISTRATION", new UserRegistration());
        commandMap.put("GO_TO_SIGN_IN", new GoToSignIn());
        commandMap.put("USER_SIGN", new UserSignIn());
    }



    public Command getCommand(String commandName){
        Command command=commandMap.get(commandName);
        return command;
    }

}
