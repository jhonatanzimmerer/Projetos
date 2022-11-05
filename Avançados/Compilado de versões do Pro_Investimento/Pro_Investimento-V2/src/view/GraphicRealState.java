package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class GraphicRealState extends JFrame{

    private JPanel PanelMain;
    private JPanel jPanelGraphic;
    private JButton button1;

    public GraphicRealState(){
        this.setDefaultCloseOperation(GraphicRealState.DISPOSE_ON_CLOSE);
        this.setContentPane(PanelMain);
        this.setSize(800,800);
        setLocationRelativeTo(null);
        this.setVisible(true);

        var reta1 = new XYSeries("Carteira");
        var reta2 = new XYSeries("Selic");
        var reta3 = new XYSeries("Preço Medio");

        reta1.add(0,2);
        reta2.add(0,1);

        reta1.add(1,4);
        reta2.add(1,2);

        reta1.add(2,8);
        reta2.add(2,10);

        reta1.add(3,20);
        reta2.add(3,15);

        var dataset = new XYSeriesCollection();

        dataset.addSeries(reta1);
        dataset.addSeries(reta2);

        JFreeChart chart = ChartFactory.createXYLineChart("Tabela de Teste","Meses","Patrimonio", dataset,
                PlotOrientation.VERTICAL,true,true,false);

        XYPlot plot = chart.getXYPlot();
        var renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0,new BasicStroke(5.0f));
        renderer.setSeriesPaint(1, Color.YELLOW);
        renderer.setSeriesStroke(1, new BasicStroke(5.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setForegroundAlpha(0.9f);
        plot.setRangeGridlinePaint(Color.red);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.black);
        plot.setDomainGridlinesVisible(true);

        chart.getLegend().setFrame(BlockBorder.NONE);


        //ChartFrame frame1 = new ChartFrame("Graficos de linha", chart);
        //frame1.setVisible(true);
        //frame1.setSize(1300,800);
        ChartPanel panel = new ChartPanel(chart);
        this.setContentPane(panel);



        //this.setVisible(true);

        //https://www.youtube.com/watch?v=7ukZUJKNCC4
        /*ChartPanel chartPanel = new ChartPanel(chart);
        jPanelGraphic.add(chartPanel);
        chartPanel.setVisible(true);

        this.setDefaultCloseOperation(GraphicRealState.DISPOSE_ON_CLOSE);
        this.setContentPane(PanelMain);
        this.setSize(800,800);
        this.setVisible(true);*/

        /*DefaultPieDataset pieDataset = new DefaultPieDataset()
        ds.addValue( 0, “a”, “x” );
        ds.addValue( 2.3, “a”, “y” );

        ds.addValue( 8, “b”, “x” );
        ds.addValue( 1.5, “b”, “y” );

        ds.addValue( 0.5, “c”, “x” );
        ds.addValue( 7, “c”, “y” );

        JFreeChart chart = ChartFactory.createLineChart(
                “Teste”,
                “Categorias”,
                “Valores”, ds,
                PlotOrientation.HORIZONTAL, true, true, true );

        CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint( 0, new Color( 0, 255, 0 ) );
        renderer.setSeriesPaint( 1, Color.YELLOW );
        renderer.setSeriesPaint( 2, new Color( 0, 100, 155 ) );

        ChartPanel panel = new ChartPanel( chart );
        jf.add( panel, BorderLayout.CENTER );*/
    }

    public void month(){

    }

    public void year(){

    }
}
