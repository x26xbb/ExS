/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author Usuario Exio
 */
public class NewClass {
//    public void update_Table2(){
//         ArrayList<Estudiante> estudiantes = controller.getEstudiantes();
//
//         DefaultTableModel modelo = (DefaultTableModel) table_estudiantes.getModel();
//
//         while (modelo.getRowCount() != 0) {
//               modelo.removeRow(0);
//          }                    
//          for (int i = 0; estudiantes != null && i < estudiantes.size(); i++) {
//               Object[] array = estudiantes.get(i).toArray();
//               array[8] = controller.getCarrera(Integer.parseInt((String) array[8]));
//               if (checkEstudiantes.isSelected()) {
//                   String anio = (String) cbAnioEstudiante.getSelectedItem();
//                   String ciclo = (String) cbCicloEstudiante.getSelectedItem();
//                   String carrera=carreracbEstudiante.getSelectedItem()+"";
//                   
//                   
//               }else{
//                    modelo.addRow(array);    
//               }
//          }
//    }
    
    /*
     *  private void update_tableEstudiantes(boolean query) {
            ArrayList<Estudiante> estudiantes = controller.getEstudiantes();
            if (query) {
               if (estudiantes != null) {
                   estudiantes.clear();
               }
           }

           DefaultTableModel modelo = (DefaultTableModel) table_estudiantes.getModel();

           while (modelo.getRowCount() != 0) {
               modelo.removeRow(0);
           }                     
         
//CAMBIAR LA CONSULTA A RETORNAR LISTA DE ESTUDIANTES ANIo CICLO CARRERA
           for (int i = 0; estudiantes != null && i < estudiantes.size(); i++) {
               Object[] array = estudiantes.get(i).toArray();
               array[8] = controller.getCarrera(Integer.parseInt((String) array[8]));
               if (checkEstudiantes.isSelected()) {
                   String anio = (String) cbAnioEstudiante.getSelectedItem();
                   String ciclo = (String) cbCicloEstudiante.getSelectedItem();
                   String carrera=carreracbEstudiante.getSelectedItem()+"";
                   if(carrera.equals("--")){
                       ArrayList<Estudiante> filtrados = controller.getEstudiantes(Integer.parseInt(anio),ciclo,controller.getCarreras(carrera));
                        Object[] array1;String nomCarrera="";
                        if(filtrados!=null && filtrados.size()>0){
                             for (int j = 0; j < filtrados.size(); j++) {
                                   array1 = filtrados.get(j).toArray();
                                   nomCarrera=controller.getCarrera(Integer.parseInt((String) array1[8]));
                                   modelo.addRow(array1);
                             }
                        }
                   }else{
                     ArrayList<Estudiante> filtrados = controller.getEstudiantes(Integer.parseInt(anio),ciclo,controller.getCarreras(carrera));
                     Object[] array1;String nomCarrera="";
                     if(filtrados!=null && filtrados.size()>0){
                        array1 = filtrados.get(0).toArray();
                        nomCarrera=controller.getCarrera(Integer.parseInt((String) array1[8]));
                     }
                     for (int j = 0; filtrados != null && j < filtrados.size(); j++) {
                         array1 = filtrados.get(j).toArray();
                         array1[8] = nomCarrera;
                         modelo.addRow(array1);                                
                     }
                     i= estudiantes.size();
                   }
               } else {
                   modelo.addRow(array);
               }
           }
     }
     */
}
