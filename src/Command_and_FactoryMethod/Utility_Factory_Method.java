package Command_and_FactoryMethod;
public class Utility_Factory_Method {
    public static CommandFactoryIF createCommandFactoryObject() {
        return new CommandFactory();
    }
}
