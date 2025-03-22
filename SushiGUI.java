import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

abstract class SushiPlate extends JPanel {
    private BufferedImage plateImage;
    protected String description = "A tasty ";

    public SushiPlate() {
        loadImage();
    }

    protected abstract void loadImage();

    protected void setPlateImage(String filePath) {
        try {
            plateImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected BufferedImage getPlateImage() {
        return plateImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (plateImage != null) {
            g.drawImage(plateImage, 0, 0, this);
        }
    }
}

class SushiWithRice extends SushiPlate {
    private BufferedImage riceImage;

    public SushiWithRice() {
        super();
        description += "sushi with ";
    }

    @Override
    protected void loadImage() {
        setPlateImage("plate.png");
        try {
            riceImage = ImageIO.read(new File("rice.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (riceImage != null) {
            g.drawImage(riceImage, 0, 0, this);
        }
    }
}

class SushiWithFish extends SushiWithRice {
    private BufferedImage fishImage;
    private static final String[] fishOptions = {"salmon.png", "tuna.png"};
    private static final String[] fishNames = {"salmon", "tuna"};
    
    public SushiWithFish() {
        super();
        int choice = (int) (Math.random()*2);
        description += fishNames[choice] + " and ";
        try {
            fishImage = ImageIO.read(new File(fishOptions[choice]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fishImage != null) {
            g.drawImage(fishImage, 0, 0, this);
        }
    }
}

class SushiWithWasabi extends SushiWithFish {
    private BufferedImage wasabiImage;

    public SushiWithWasabi() {
        super();
        description += "wasabi!";
    }

    @Override
    protected void loadImage() {
        super.loadImage();
        try {
            wasabiImage = ImageIO.read(new File("wasabi.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (wasabiImage != null) {
            g.drawImage(wasabiImage, 0, 0, this);
        }
        g.setColor(Color.BLACK);
        g.drawString(description, 10, 20);
    }
}

public class SushiGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sushi Inheritance GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);
            
            SushiPlate sushi = new SushiWithWasabi();
            
            frame.add(sushi);
            frame.setVisible(true);
        });
    }
}