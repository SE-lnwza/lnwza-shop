package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author SE-lnwza
 */
public class SceneLoader {
    
    private static final String OWNER_HOMEPAGE = "StockView";
    private static final String AGENT_HOMEPAGE = "PurchaseHome";
    
    private static final String CSS_PATH = "/ui/resources/";
    private static final String FXML_PATH = "/ui/fxml/";
    private static final int MAIN_WIDTH = 1024;
    private static final int MAIN_HEIGHT = 768;
    private static final int POPUP_WIDTH = 600;
    private static final int POPUP_HEIGHT = 400;
    
    private static Stage main, pop, login;
    private static BorderPane mainRoot;
    private static VBox pcRoot;
    private static Scene mainScene, popScene, loginScene;
    private static Parent mainMenu, mainBody, popBody, loginBody, pcMenu, pcBody;
    private static FXMLLoader popFXML;
    
    public static void initialize(Stage stage) {
        main = new Stage();
        pop = new Stage();
        login = stage;
        mainRoot = new BorderPane();
        pcRoot = new VBox();
        mainScene = new Scene(mainRoot, MAIN_WIDTH, MAIN_HEIGHT);
        
        loadLogin();
    }
    
    public static void loadLogin() {
        main.close();
        pop.close();
        
        login.setTitle("Login - lnwza SHOP");
        loginBody = load("Login");
        loginScene = new Scene(loginBody, POPUP_WIDTH, POPUP_HEIGHT);
        loadCss(loginScene);
        login.setScene(loginScene);
        login.show();
    }
    
    public static void loadMain() {
        login.close();
        
        main.setTitle("lnwza SHOP");
        mainMenu = load("MenuBar");
        mainRoot.setTop(mainMenu);
        if (Session.isOwner()) {
            setBody(OWNER_HOMEPAGE);
        } else {
            loadPurchase();
//            setBody(AGENT_HOMEPAGE);
        }

//        scene = new Scene(root, MAIN_WIDTH, MAIN_HEIGHT);
        loadCss(mainScene);
        main.setScene(mainScene);
        main.show();
    }
    
    public static void loadPurchase() {
        pcMenu = load("PurchaseMenu");
        pcBody = load(AGENT_HOMEPAGE);
        
        pcRoot.getChildren().addAll(pcMenu, pcBody);
        mainRoot.setCenter(pcRoot);
    }
    
    public static void setBody(String name) {
        mainBody = load(name);
        mainRoot.setCenter(mainBody);
    }
    
    public static void setPCBody(String name) {
        pcBody = load(name);
        pcRoot.getChildren().set(1, pcBody);
        mainRoot.setCenter(pcRoot);
    }
    
    public static void popup(String name, String title) {
        popFXML = loadFXML(name);
        try {
            popBody = popFXML.load();
        } catch (IOException ex) {
        }
        popScene = new Scene(popBody, POPUP_WIDTH, POPUP_HEIGHT);
        loadCss(popScene);
        pop.setScene(popScene);
        pop.setTitle(title);
        pop.show();
    }
    
    public static void popup(String name) {
        popup(name, "lnwza SHOP");
    }
    
    public static <T> T getPopupController(Class<T> controller) {
        return controller.cast(popFXML.getController());
    }
    
    public static <T> T getPopupController() {
        return popFXML.getController();
    }
    
    private static FXMLLoader loadFXML(String name) {
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(SceneLoader.class.getResource(FXML_PATH + name + ".fxml"));
        } catch (Exception ex) {       
        }
        return fxmlLoader;
    }
    
    private static Parent load(String name) {
        if (name.equals("OrderView")) {
            name += Session.getRole();
        }
        
        Parent parent = null;
        System.out.println("[GUI] Loading " + name + "...");
        try {
            parent = loadFXML(name).load();
        } catch (IOException ex) {
            System.out.println("Cannot load " + name + "!");
            System.exit(0);
        }
        System.out.println("[GUI] " + name + " loaded!");
        return parent;
    }
    
    private static void loadCss(Scene scene) {
        System.out.println("[GUI] Adding CSS...");
        for (String fileName : getCssFileName()) {
            scene.getStylesheets().add(CSS_PATH + fileName);
        }
        System.out.println("[GUI] CSS added!");
    }
    
    private static ArrayList<String> getCssFileName() {
        ArrayList<String> result = new ArrayList<>();
        File folder = new File("src/" + CSS_PATH);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".css")) {
                result.add(file.getName());
            }
        }
        return result;
    }
    
}