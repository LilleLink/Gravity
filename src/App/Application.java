package App;

import controller.PauseController;
import model.Astromath;
import model.Model;
import view.GravityView;

// TODO: https://app.diagrams.net/#G1fkylgg9SZ79bVJ9xB83P9qAtFSf0ML9h
public class Application implements Runnable {

    private GravityView view;
    private Model model;

    private Thread thread;

    private static int frameCount;
    private static boolean paused;

    public static void main(String[] args) {
        new Application().start();
    }

    private void start() {
        model = new Model();
        view = new GravityView("Gravity", model);
        view.getPaintFrame().addKeyListener(new PauseController());
        thread = new Thread(this);
        thread.start();
        paused = false;
    }

    public static int getTimeCount() {
        return frameCount;
    }

    //TODO Move to model, view doesnt need to be paused
    public static void pause() { paused = !paused;
        System.out.println(paused);
    }

    @Override
    public void run()
    {
        while (true) {
            if (!paused) {
                model.update();
                frameCount += Astromath.timeFactor;
                try {
                    thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            view.update();
        }
    }
}
