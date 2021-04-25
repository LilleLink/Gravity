package App;

import model.Astromath;
import model.Model;
import view.GravityView;

import java.util.Timer;
import java.util.TimerTask;

public class Application implements Runnable{

    private GravityView view;
    private Model model;

    private Thread thread;

    private Timer timer;
    private static int timeCount;

    public static void main(String[] args) {
        new Application().start();
    }

    private void start() {
        model = new Model();
        view = new GravityView("Gravity", model);
        thread = new Thread(this);
        thread.start();
        initTimer();
    }

    private void initTimer()
    {
        timer = new Timer();
        TimerTask tt = new TimerTask()
        {
            @Override
            public void run()
            {
                timeCount += Astromath.timeFactor;
            }
        };
        timer.scheduleAtFixedRate(tt, 1000, 1000);
    }

    public static int getTimeCount() {
        return timeCount;
    }

    @Override
    public void run()
    {
        while (true) {
            model.update();
            view.update();

            try {
                thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
