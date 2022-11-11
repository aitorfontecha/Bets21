package businessLogic;

import domain.Apostua;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class UserAdapter extends AbstractTableModel{

    private Vector<Apostua> apustuak;
    private String[] colNames = {"Event", "Question", "Event date", "Bet (€)"};

    public UserAdapter(Vector<Apostua> apustuak){
        this.apustuak = apustuak;
    }

    public String getColName(int i){
        return colNames[i];
    }

    public Class getColClass(int i){
        if(i == 2){
            return Double.class;
        }
        else{
            return String.class;
        }
    }

    @Override
    public int getRowCount(){
        int zenbat;
        if(apustuak == null){
            zenbat = 0;
        }
        else{
            zenbat = apustuak.size();
        }
        return zenbat;
    }

    @Override
    public int getColumnCount(){
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        if(columnIndex == 0){
            return apustuak.get(rowIndex).getPronostikoa().get(0).getGaldera().getEvent();
        }
        else if(columnIndex == 1){
            return apustuak.get(rowIndex).getPronostikoa().get(0).getGaldera();
        }
        else if(columnIndex == 2){
            return apustuak.get(rowIndex).getPronostikoa().get(0).getGaldera().getEvent().getEventDate();
        }
        else{
            return apustuak.get(rowIndex).getPronostikoa().get(0).getKuota();
        }
    }
}
