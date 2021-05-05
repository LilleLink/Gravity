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

    public static void main(String[] args) {
        new Application().start();
    }

    private void start() {
        model = new Model();
        view = new GravityView("Gravity", model);
        view.getPaintFrame().addKeyListener(new PauseController());
        thread = new Thread(this);
        thread.start();
    }

    public static int getTimeCount() {
        return frameCount;
    }

    @Override
    public void run()
    {
        while (true) {
            model.update();
            view.update();
            frameCount += Astromath.timeFactor;

            try {
                thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
