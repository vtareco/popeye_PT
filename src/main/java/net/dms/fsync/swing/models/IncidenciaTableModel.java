package net.dms.fsync.swing.models;

import net.dms.fsync.settings.Internationalization;
import net.dms.fsync.synchronizer.fenix.entities.FenixIncidencia;
import net.dms.fsync.synchronizer.fenix.entities.enumerations.TableColumnEnumType;
import net.dms.fsync.swing.components.JenixTableModel;
import net.dms.fsync.swing.components.SelectOption;

import java.util.List;

/**
 * Created by dminanos on 22/05/2017.
 */
public class IncidenciaTableModel extends JenixTableModel<FenixIncidencia, IncidenciaTableModel.Columns> {

    public enum Columns implements TableColumnEnumType {

       ID_INCIDENCIA(false, 50, Internationalization.getStringTranslated("keyUper")),
       NOMBRE_INCIDENCIA(true, 50, Internationalization.getStringTranslated("keyUper")),
        LOCALIZADA_EN(true, 50, Internationalization.getStringTranslated("keyUper")),
        TIPO_INCIDENCIA(true, 50, Internationalization.getStringTranslated("keyUper")),
        DESCRIPCION(true, 50, Internationalization.getStringTranslated("keyUper")),
        FECHA_INICIO(true, 50, Internationalization.getStringTranslated("keyUper")),
        FECHA_FIN(true, 50, Internationalization.getStringTranslated("keyUper")),
        ESFUERZO_HH(true, 50, Internationalization.getStringTranslated("keyUper")),
        URGENCIA(true, 50, Internationalization.getStringTranslated("keyUper")),
        IMPACTO(true, 50, Internationalization.getStringTranslated("keyUper")),
        PRIORIDAD(true, 50, Internationalization.getStringTranslated("keyUper")),
        FECHA_PREVISTA_CENTRO(true, 50, Internationalization.getStringTranslated("keyUper")),
        TAREA_CAUSANTE(true, 250, Internationalization.getStringTranslated("keyUper")),
        OT_CORRECTOR(true, 50, Internationalization.getStringTranslated("keyUper")),
        ACC_CORRECTOR(true, 250, Internationalization.getStringTranslated("keyUper")),
        ESTADO(true, 80, Internationalization.getStringTranslated("keyUper"));

       private int width;
        private String columnName;
        private boolean editable;

        Columns(boolean editable, int width,String columnName) {
            this.editable = editable;
            this.width = width;
            this.columnName = columnName;

        }

        public boolean isEditable() {
            return editable;
        }

        public String getColumnName() {
            return columnName;
        }

        public int getWidth(){
            return width;
        }
    }




    public IncidenciaTableModel(List<FenixIncidencia> incidencias){
        super(Columns.class, incidencias);
    }

    @Override
    protected String getValueToDisplay(Columns enumC) {
        return enumC.getColumnName();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Columns c = findColumnTypeByOrdinal(columnIndex);
        FenixIncidencia r = getList().get(rowIndex);
        switch (findColumnTypeByOrdinal(c.ordinal())){
            case ACC_CORRECTOR:
                 return new SelectOption<>(r.getAccCorrector(), r.getAccCorrector());
            case TAREA_CAUSANTE:
                return new SelectOption<>(r.getTareaCausante(), r.getTareaCausante());
            default:
                return super.getValueAt(rowIndex,columnIndex);
        }


    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        FenixIncidencia incidencia = rows.get(row);
        switch (findColumnTypeByOrdinal(col)){
            case ACC_CORRECTOR:
                if (value != null) {
                    SelectOption<String,String> opt = (SelectOption)value;
                    incidencia.setAccCorrector(opt == null ? null : opt.getCode());
                }
                break;
            case TAREA_CAUSANTE:
                if (value != null) {
                    SelectOption<String,String> opt = (SelectOption)value;
                    incidencia.setTareaCausante(opt == null ? null : opt.getCode());
                }
                break;
            default:
                    super.setValueAt(value, row, col);
        }
    }


}