

public class App extends Aplication{
    @Override
    public void start(Stage mainWindow){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UI.java"));
            Parent root = new Scene(root);

            mainWindow.setTitle("WireWorld");
            mainWindow.setScene(scene);
            mainWindow.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        launch(args);
    }


}