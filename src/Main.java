import Characters.Characters;
import Map.Map;
import User.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import Map.mapEvents.eventType.Fight;
import java.io.File;
import java.util.ArrayList;

/**
 * This is the start of the main application
 */
public class Main extends Application {
    /**
     * This is the root that will be used to create our GUI
     */
    Pane root, portW, targ;
    /**
     * Scene used to show the user said GUI
     */
    Scene scene;
    /**
     * All possible stages to be used (some go unused, they are meant for future additions to this project)
     */
    Stage genStage;
    /**
     * Thread used for the animation function
     */
    Thread t;
    int state = 1;
    int prevState;
    boolean ITD = false;
    boolean notSet = true;
    boolean inPortrait = true;
    /**
     * Player object is initialized, will be used later
     */
    Player playerUser = new Player();
    PlayerMap playerMap1 = new PlayerMap();
    /**
     * Map.Map object is initialized and given a single argument that indicates that it is currently not holding an actual map,
     * will be required for future random generation of a map
     */
    Map muhMap = new Map("Not Yet");
    javafx.scene.control.Button btn_Start, btn_returnCamp, btn_dificultyT, btn_Inventory, btn_Stats, btn_ETD, btn_Settings, btn_party, btn_parseTheRubble, btn_return, btn_setFight;
    String musicFile;
    Media sound;
    MediaPlayer mediaPlayer;
    MediaView muhMediaVi;
    Fight fSetUp;
    ImageView targImg;
    int combatTarg;

    /**
     * Change music is a function which changes the musicFile being reproduced based on the current root element
     * it takes a single argument (String) which is given from the GUI construction and it is a string referencing an
     * mp3 file on the package
     * @param newFile string URL referencing the music file.
     */
    public void changeMusic(String newFile){
        musicFile = "resources/musicTrack/" + newFile;
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource(musicFile).toExternalForm()));
        mediaPlayer.play();
        mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
        muhMediaVi = new MediaView(mediaPlayer);
        root.getChildren().add(muhMediaVi);
    }

    public void changeBackground(String backgroundUrl){
        root.setBackground(new Background(new BackgroundImage(new Image(backgroundUrl, scene.getWidth(), scene.getHeight(), true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(scene.getWidth(), scene.getHeight(), true, true, true, true))));
    }

    /**
     * This is used to both stop the current song from being played and
     * declare a new GUI stage to be shown to the user
     * goIntoCamp
     * @link goIntoCamp()
     */
    public void sceneStarter(){
        mediaPlayer.stop();
        try {
            start(genStage);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * This event is used by two things for two specific purposes, it serves as a way to go from
     * the main menu into the 2nd stage, which is the theoretical camp of the player, and if the player
     * is already on the 3rd stage AKA the dungeon, it will only work if they are on the entrance
     * (Marked by Green), this inevitably calls sceneStarter().
     * @param e Triggering action event.
     * @link sceneStarter()
     */
    public void goIntoCamp(ActionEvent e){
        System.out.println(e.getSource().toString().charAt(10));
        if (state == 3){
            if (playerMap1.getxPos() == playerMap1.getSpawnLoc().getLocX() && playerMap1.getyPos() == playerMap1.getSpawnLoc().getLocY()){
                prevState = state;
                state = 2;
                sceneStarter();
            }
        }
        else {
            prevState = state;
            state = 2;
            notSet = true;
            sceneStarter();
        }
    }

    /**
     * Short for "Enter the Dungeon" this calls for sceneStarter() and makes it so the
     * game will go into stage 3, which is the dungeon.
     * @param e Triggering action event.
     * @link sceneStarter()
     * @see Map
     */
    public void ETD(ActionEvent e){
        prevState = state;
        state = 3;
        notSet = true;
        sceneStarter();
    }

    public void startFight(ActionEvent e){
        prevState = state;
        state = 4;
        sceneStarter();
    }

    /**
     * This button still doesn't have much use, it is meant to be able to boot the player outside the Dungeon
     * and back without the dungeon reseting, it only works if a dungeon has already been initialized with a map
     * @param e Triggering action event.
     * @link sceneStarter()
     * @see Map
     */
    public void goBack(ActionEvent e){
        if (!muhMap.getInit().equals("Not Yet")){
            int sv = state;
            state = prevState;
            prevState = sv;
            sceneStarter();
        }
    }

    public void parseRubble(ActionEvent e){
        playerMap1.setClearRubble(true);
    }
    public void setOutPortrait(MouseEvent me){inPortrait = false; removePortrait(me);}
    public void showPortrait(MouseEvent me){
        root.getChildren().remove(portW);
        Characters toPortrait = fSetUp.getEnemies().get(Integer.parseInt(me.getPickResult().getIntersectedNode().getId()));

        portW = new Pane();
        ImageView img = new ImageView(toPortrait.getPortrait());
        img.setFitWidth(80);
        img.setFitHeight(80);

        GridPane stats = toPortrait.getMyPorPane();

        portW.getChildren().addAll(img, stats);
        img.relocate(10, 20);
        stats.relocate(100, 10);
        stats.setHgap(5);

        root.getChildren().add(portW);
        portW.relocate(me.getSceneX() - 80, me.getSceneY() - 80);
        portW.setPrefSize(225, 120);
        portW.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.5), null, null)));

        portW.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this::setOutPortrait);
        portW.getChildren().forEach(node -> {node.setId(me.getPickResult().getIntersectedNode().getId()); node.addEventHandler(MouseEvent.MOUSE_PRESSED, this::selectTarget);});
        inPortrait = true;
    }

    public void removePortrait(MouseEvent me){
        if (!inPortrait){root.getChildren().remove(portW); inPortrait = true;}
    }

    public void selectTarget(MouseEvent me){
        try{
            targImg = new ImageView(fSetUp.getEnemies().get(Integer.parseInt(me.getPickResult().getIntersectedNode().getId())).getPortrait());
        }catch (NumberFormatException exception){
            System.out.println("Try again");
            targImg = new ImageView();
            combatTarg = 0;
            return;
        }
        targImg.setFitHeight(40);
        targImg.setFitWidth(40);
        targ.getChildren().add(targImg);
        targImg.relocate(60, 65);
        combatTarg = Integer.parseInt(me.getPickResult().getIntersectedNode().getId());
        System.out.println("My target is: " + combatTarg);
    }


        /**
         * This is actually now what sends the program into different "state-based" stages depending
         * on a global integer, it is like a fork in the road per se which checks for the current state
         * and sets up the UI the way the current state demands it.
         *
         * @param stage The main stage
         * @throws Exception idk man
         * @link mainMenu()
         * @link homeBase()
         * @link dungeonUI()
         */
    @Override
    public void start(Stage stage) throws Exception {
        genStage = stage;
        if(state == 1) {
            mainMenu();
        } else if (state == 2) {
            homeBase();
        } else if (state == 3){
            dungeonUI();
        } else if (state == 4) {
            fightUI();
        }
        genStage.show();
    }

    /**
     * This is our main menu stage GUI design associated with state 1
     */
    public void mainMenu(){
        root = new Pane();
        scene = new Scene(root, 700, 600);
        genStage.setTitle("FrostByte");
        genStage.setScene(scene);
        genStage.setMaxWidth(700);
        genStage.setMaxHeight(600);

        btn_Start = new Button("Begin Game");
        btn_dificultyT = new Button("Set difficulty");
        btn_Settings = new Button("Settings");
        root.getChildren().addAll(btn_Start, btn_dificultyT, btn_Settings);
        changeBackground("resources/entrance.png");
        changeMusic("binarySignal.mp3");
        Button[] nodes = {btn_Start, btn_dificultyT, btn_Settings};
        int relocX = 40;
        int relocY = 80;
        for (Button x: nodes) {
            x.setMinWidth(100.0);
            x.relocate(relocX, relocY);
            relocY += 40;
        }
        btn_Start.setId("1");
        btn_Start.setOnAction(this::goIntoCamp);
    }

    /**
     * This is our main "homebase" stage GUI design associated with state 2
     */
    public void homeBase(){
        root = new Pane();
        genStage.setTitle("The light before the dark...");

        btn_ETD = new Button("Enter new Dungeon");
        btn_Inventory = new Button("Open inventory");
        btn_Stats = new Button("See stats");
        btn_party = new Button("See your party");
        btn_return = new Button("Return to the dungeon");
        root.getChildren().addAll(btn_ETD, btn_Stats, btn_Inventory, btn_party, btn_return);
        changeMusic("rubbleOfTheForgotten.mp3");

        Button[] nodes = {btn_ETD, btn_Stats, btn_Inventory, btn_party};
        int relocX = 40;
        int relocY = 20;

        for (Button x: nodes) {
            x.relocate(relocX, relocY);
            relocY += 40;
        }

        btn_return.relocate(240, 180);

        scene = new Scene(root, 700, 600);
        changeBackground("resources/campfireS.png");
        genStage.setScene(scene);
        genStage.setMaxWidth(700);
        genStage.setMaxHeight(600);

        btn_ETD.setOnAction(this::ETD);
        btn_return.setOnAction(this::goBack);
    }

    /**
     * This is our main "dungeon" stage GUI design associated with state 3,
     * this one in particular launches a thread used for animation
     */
    public void dungeonUI(){
        root = new Pane();
        scene = new Scene(root, 900, 700);
        genStage.setScene(scene);
        genStage.setWidth(900);
        genStage.setHeight(700);
        genStage.setMaxWidth(900);
        genStage.setMaxHeight(700);

        Canvas canvas = new Canvas(900, 600);
        btn_returnCamp = new Button("Exit the dungeon");
        btn_parseTheRubble = new Button("Clear a way");
        btn_party = new Button("Go to Camp");
        btn_setFight = new Button("Fight");

        root.getChildren().addAll(canvas, btn_returnCamp, btn_parseTheRubble, btn_party, btn_setFight);

        btn_returnCamp.relocate(100, 605);
        btn_parseTheRubble.relocate(220, 605);
        btn_party.relocate(700 , 605);
        btn_setFight.relocate(-100, 290);
        changeMusic("desertedAmbiance.mp3");
        genStage.setTitle("You've entered the Dungeon");
        genStage.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                if(playerMap1.getClearRubble()) {
                    switch (event.getCode()) {
                        case W -> {
                            muhMap.makeWay(playerMap1.moveRubble("up"));
                            muhMap.setGrid(playerMap1.getGridCopy());
                            playerMap1.setAvailableSpace(muhMap.giveCorridors());
                        }
                        case A -> {
                            muhMap.makeWay(playerMap1.moveRubble("left"));
                            muhMap.setGrid(playerMap1.getGridCopy());
                            playerMap1.setAvailableSpace(muhMap.giveCorridors());
                        }
                        case S -> {
                            muhMap.makeWay(playerMap1.moveRubble("down"));
                            muhMap.setGrid(playerMap1.getGridCopy());
                            playerMap1.setAvailableSpace(muhMap.giveCorridors());
                        }
                        case D -> {
                            muhMap.makeWay(playerMap1.moveRubble("right"));
                            muhMap.setGrid(playerMap1.getGridCopy());
                            playerMap1.setAvailableSpace(muhMap.giveCorridors());
                        }
                    }
                } else switch (event.getCode()){
                    case W ->{
                        playerMap1.movePlayer("up");
                    }
                    case A ->{
                        playerMap1.movePlayer("left");
                    }
                    case S ->{
                        playerMap1.movePlayer("down");
                    }
                    case D ->{
                        playerMap1.movePlayer("right");
                    }
                }

                if (event.getCode() == KeyCode.ESCAPE){
                    ITD = false;
                }
            }
        });
        btn_returnCamp.setOnAction(this::goIntoCamp);
        btn_parseTheRubble.setOnAction(this::parseRubble);
        btn_party.setOnAction(this::goBack);
        btn_setFight.setOnAction(this::startFight);

        if (notSet){
            muhMap = new Map(gc, 250);
            playerMap1.setLimits(canvas.getWidth(), canvas.getHeight());
            playerMap1.setSpawn(muhMap);
            notSet = false;
        }

        playerMap1.setPlaying(true);

        t = new Thread(() -> animate1(gc, muhMap));
        t.start();
    }

    public void fightUI(){
        root = new Pane();
        scene = new Scene(root, 900, 650);
        genStage.setScene(scene);
        genStage.show();
        scene.setFill(Color.BLACK);
        genStage.setTitle("You've entered a fight");
        genStage.setMaxWidth(900);
        genStage.setMaxHeight(650);

        fSetUp = (Fight) playerMap1.getEncounter().getAnEvent();
        fSetUp.getEnemies().forEach(characters -> characters.generatePorPane());
        Characters[] myParty = playerUser.getParty();
        changeMusic(fSetUp.getMusicTrackRef());
        changeBackground(fSetUp.getBackground());

        for (int x = 0; x < fSetUp.getEnemies().size(); x++){
            ImageView anImage = new ImageView();
            anImage.setImage(fSetUp.getEnemies().get(x).getSprite());
            anImage.setFitHeight(150);
            anImage.setFitWidth(135);
            HBox enemBox = new HBox(anImage);
            enemBox.setId("" + x);
            anImage.setId("" + x);
            root.getChildren().add(enemBox);
            switch (x){
                case 0 ->{enemBox.relocate(480, 380);}
                case 1 ->{enemBox.relocate(570, 290);}
                case 2 ->{enemBox.relocate(590, 440);}
                case 3 ->{enemBox.relocate(690, 320);}
                case 4 ->{enemBox.relocate(700, 420);}
            }
            enemBox.addEventHandler(MouseEvent.MOUSE_ENTERED, this::showPortrait);
            enemBox.addEventHandler(MouseEvent.MOUSE_EXITED, this::removePortrait);

        }

        ImageView myChara = new ImageView();
        myChara.setImage(myParty[0].getSprite());
        myChara.setFitHeight(120.0);
        myChara.setFitWidth(130.0);

        targ = new Pane(new ImageView(new Image("resources/FightUI/targetSign.png", 200, 130, true, true)));

        Pane rost = new Pane();
        rost.setBackground(new Background(new BackgroundImage(new Image("resources/FightUI/lineup.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(100, 100, true, true, true, true))));
        rost.setPrefSize(550, 130);

        root.getChildren().addAll(myChara, targ, rost);

        myChara.relocate(200, 360);
        rost.relocate(175, 20);
        targ.relocate(520, 115);

        /*

        This not only works but fight does
        indeed run and is able to run it while the rest of the GUI functions

        t = new Thread(this::fightSequence);
        t.start();

        */
    }

    /**
     * Animation where the actual map of our dungeon is being shown
     * @param gc graphics context in which to draw the dungeon
     * @param dungeon dungeon object to be drawn
     */
    public void animate1(GraphicsContext gc, Map dungeon) {
        ITD = true;
        int numeventsFound = 0;
        while (ITD) {
            dungeon.drawMap(gc, playerMap1.getSpawnLoc());
            playerMap1.drawPlayer(gc);
            if(playerMap1.checkEvent()){
                playerMap1.setPlaying(false);
                numeventsFound++;
                System.out.println("Player has found event, num " + numeventsFound + " and it is: " + playerMap1.getFacing());
                if (playerMap1.getFacing().equals("Fight")) { // Proof of concept
                    btn_setFight.relocate(200, 200);
                }
            }
            pause(100);
        }
    }

    public void fightSequence() {
        int x = 0;
        while (true){
            System.out.println(x);
            x++;
        }
    }

    /**
     * Use this method instead of Thread.sleep(). It handles the possible
     * exception by catching it, because re-throwing it is not an option in this
     * case.
     *
     * @param duration Pause time in milliseconds.
     */
    public static void pause(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
            System.out.println("Something was here but now I forgor");
        }
    }

    /**
     * Exits the app completely when the window is closed. This is necessary to
     * kill the animation thread.
     */
    @Override
    public void stop() {
        System.exit(0);
    }

    /**
     * Launches the app
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}