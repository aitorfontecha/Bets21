package businessLogic;

import domain.Bezero;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Apostua;

public class BezeroaAdapter extends AbstractTableModel {

    private List<Apostua> apostuak;
    private Bezero bezeroa;
    private String[] colNames = new String[] {"Event", "Question", "Event Date", "Bet(€)"};

    public BezeroaAdapter(Bezero b) {
        this.bezeroa = b;
        this.apostuak = new ArrayList<>(b.getApostuak());
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        switch(colIndex) {
            case 0:
                return apostuak.get(rowIndex).getPronostikoa().get(0).getGaldera().getEvent().getDescription();
            case 1:
                return apostuak.get(rowIndex).getPronostikoa().get(0).getGaldera().getQuestion();
            case 2:
                return apostuak.get(rowIndex).getPronostikoa().get(0).getGaldera().getEvent().getEventDate();
            case 3:
                return apostuak.get(rowIndex).getApustuDiru();
        }
        return null;
    }

    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return apostuak.size();
    }

}