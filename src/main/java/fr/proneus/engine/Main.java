package fr.proneus.engine;

import fr.proneus.engine.demo.FileDialogState;

public class Main {

    public static void main(String[] args) {

        runState(new FileDialogState());
    }

    private static void runState(State state) {
        int cameraWidth = 512;
        int cameraHeight = 288;

        String name = "Motable Client";

        Application app = new Application(name,
                1920, 1080,
                cameraWidth, cameraHeight,
                state);

        app.setCloseCallBack(game -> {
            System.out.println("Game closed!");
        });

        // app.setFpsLimit(1);
        app.setTPS(60);
        app.setIcon("test2.png");
        //app.enableDiscordRPC("appId");
        app.start();
    }

}
