package by.http.autopark.controller;


import by.http.autopark.controller.commandImplements.*;
import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String,Command> commandMap=new HashMap<String, Command>();

    public CommandProvider() {

        commandMap.put(CmdEnum.MESSAGE_SUCCESS_REGISTRATION.getCommand(), new SuccessRegistration());

        commandMap.put(CmdEnum.GO_TO_REGISTRATION.getCommand(),new GoToReg());
        commandMap.put(CmdEnum.REGISTRATION.getCommand(), new RegistrationUser());
        commandMap.put(CmdEnum.GO_TO_SIGN_IN.getCommand(), new GoToSignIn());
        commandMap.put(CmdEnum.GO_TO_MAIN_MENU.getCommand(), new GoToMainMenu());
        commandMap.put(CmdEnum.USER_SIGN_IN.getCommand(),new UserSignIn());

        commandMap.put(CmdEnum.SHOW_ALL_ORDERS.getCommand(),new ShowAllOrders());
        commandMap.put(CmdEnum.GO_TO_ADD_ORDER_PAGE.getCommand(), new AddOrder());
        commandMap.put(CmdEnum.ORDER_REGISTRATION.getCommand(),new RegistrationOrder());
        commandMap.put(CmdEnum.GO_TO_DELETE_ORDERS_PAGE.getCommand(), new DeleteOrderPage());

        commandMap.put(CmdEnum.GO_TO_ADD_CAR_PAGE.getCommand(), new AddCar());
        commandMap.put(CmdEnum.CAR_REGISTRATION.getCommand(), new CarRegistration());
        commandMap.put(CmdEnum.GO_TO_DELETE_CAR.getCommand(), new DeleteCar());
        commandMap.put(CmdEnum.SHOW_ALL_CARS.getCommand(),new ShowAllCars());
        commandMap.put(CmdEnum.CHANGE_AVAILABILITY.getCommand(), new ChangeAvailability());
        commandMap.put(CmdEnum.CHANGE_AVAILABILITY_CAR.getCommand(), new ChangeAvailabilityCar());

        commandMap.put(CmdEnum.GO_APPROVE_CERTAIN_ORDER.getCommand(), new ApproveCertainOrder());
        commandMap.put(CmdEnum.GO_TO_DELETE_ORDER.getCommand(), new DeleteCertainOrder());
        commandMap.put(CmdEnum.GO_TO_APPROVE_ORDERS.getCommand(), new ApproveOrder());
        commandMap.put(CmdEnum.GO_TO_ADD_APPROVED_ORDER.getCommand(), new AddApprovedOrder());

        commandMap.put(CmdEnum.SHOW_ALL_DRIVERS_ORDERS.getCommand(), new ShowAllDriverOrder());
        commandMap.put(CmdEnum.CHANGE_STATUS_BY_DRIVER.getCommand(),new ChangeStatusByDriver());

        commandMap.put(CmdEnum.CHANGE_LANGUAGE.getCommand(), new ChangeLanguage());

    }
    public Command getCommand(String commandName){
        Command command=null;
        boolean condition=false;
        for (CmdEnum cmd:CmdEnum.values()) {
            if(commandName.equals(cmd.getCommand())){
                command=commandMap.get(commandName);
                 condition=true;
                 break;
            }
            else{
                condition=false;
            }
        }
        if(condition) {
            return command=commandMap.get(commandName);
        }
        else{
            return command=new NoCommandPage();
        }
    }

}
