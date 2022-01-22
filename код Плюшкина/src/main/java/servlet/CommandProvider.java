package servlet;


import servlet.commandImplements.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String,Command> commandMap=new HashMap<String, Command>();

    public CommandProvider() {

        commandMap.put("MESSAGE_SUCCESS_REGISTRATION", new SuccessRegistration());

        commandMap.put("GO_TO_REGISTRATION",new GoToReg());
        commandMap.put("REGISTRATION", new RegistrationUser());
        commandMap.put("GO_TO_SIGN_IN", new GoToSignIn());
        commandMap.put("GO_TO_MAIN_MENU", new GoToMainMenu());
        commandMap.put("SHOW_ALL_CARS",new ShowAllCars());
        commandMap.put("GO_TO_COMMAND_CAR",new CommandCar());
        commandMap.put("GO_TO_COMMAND_USER",new CommandUser());
        commandMap.put("SHOW_ALL_USERS",new ShowAllUsers());
        
        commandMap.put("USER_SIGN_IN",new UserSignIn());
        commandMap.put("SHOW_ALL_ORDERS",new ShowAllOrders());
        commandMap.put("GO_TO_ADD_ORDER_PAGE", new AddOrder());
        commandMap.put("ORDER_REGISTRATION",new RegistrationOrder());
        //commandMap.put("GO_TO_DELETE_ORDERS_PAGE", new DeleteOrderPage()); //DeleteOrder()
        commandMap.put("GO_TO_ADD_CAR_PAGE", new AddCar());
        commandMap.put("CAR_REGISTRATION", new CarRegistration());
        commandMap.put("GO_TO_DELETE_CAR", new DeleteCar());
        commandMap.put("GO_APPROVE_CERTAIN_ORDER", new ApproveCertainOrder());
        commandMap.put("GO_TO_DELETE_ORDERS_PAGE", new DeleteOrderPage());
        commandMap.put("GO_TO_DELETE_ORDER", new DeleteCertainOrder());

        commandMap.put("GO_TO_APPROVE_ORDERS", new ApproveOrder());
        commandMap.put("GO_TO_ADD_APPROVED_ORDER", new AddApprovedOrder());
        commandMap.put("CHANGE_AVAILABILITY_CAR", new ChangeAvailabilityCar());
        commandMap.put("CHANGE_AVAILABILITY", new ChangeAvailability());

        commandMap.put("SHOW_ALL_DRIVER'S_ORDERS", new ShowAllDriverOrder());
        commandMap.put("CHANGE_STATUS_BY_DRIVER",new ChangeStatusByDriver());

        commandMap.put("CHANGE_LANGUAGE", new ChangeLanguage());





    }



    public Command getCommand(String commandName){
        Command command=commandMap.get(commandName);
        return command;
    }

}
