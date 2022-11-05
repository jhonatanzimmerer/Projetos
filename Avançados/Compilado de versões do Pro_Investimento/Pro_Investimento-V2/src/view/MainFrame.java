package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private JPanel PanelMain;
    private JLabel jLabelImport;
    private JPanel jPanelImportAll;
    private JLabel jLabelImportManual;
    private JLabel jLabelImportExcel;
    private JLabel jLabelMenu;
    private JScrollPane JScrollPaneMenu;
    private JPanel jPanelImport;
    private JPanel JPanelPane;
    private JPanel JPanelMenu;
    private JPanel jPanelPerformance;
    private JLabel jLabelGraphic;
    private JLabel jLabelPerformanceMonth;
    private JPanel jPanelPerformnaceAll;
    private JLabel JLabelProfitability;
    private JTabbedPane tabbedPane1;
    private JPanel jPanelGraphic1;

    public MainFrame(){
        this.setSize(900,800);
        this.setContentPane(PanelMain);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
        actions();

        jLabelImportExcel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new ImportDataExcel().start();
            }
        });
        jLabelPerformanceMonth.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new PerformanceMonth();
                //GraphicRealState graphicRealState = new GraphicRealState();
                //graphicRealState.setSize(400,400);
                //graphicRealState.setVisible(true);
                //JPanelPane.add(graphicRealState.getContentPane()).revalidate();
            }
        });
        jLabelImportManual.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ImportData().setVisible(true);
            }
        });
        jPanelPerformance.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(jPanelPerformnaceAll.isVisible())
                    jPanelPerformnaceAll.setVisible(false);
                else
                    jPanelPerformnaceAll.setVisible(true);
            }
        });
        JLabelProfitability.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new GraphicRealState();
            }
        });
    }

    public void actions(){
        //Evento de clique para o menu na parte de importar
        jPanelImport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(jPanelImportAll.isVisible())
                    jPanelImportAll.setVisible(false);
                else
                    jPanelImportAll.setVisible(true);
            }
        });
        //Evento de clique no Menu
        jLabelMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(JScrollPaneMenu.isVisible())
                    JScrollPaneMenu.setVisible(false);
                else
                    JScrollPaneMenu.setVisible(true);
            }
        });
    }

    public void start(){
        this.setVisible(true);
    }

    public static Dimension screenSize(){
        GraphicsDevice Gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return new Dimension(Gd.getDisplayMode().getWidth(),Gd.getDisplayMode().getHeight());
    }
}
