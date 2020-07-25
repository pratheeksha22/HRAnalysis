package org.deeplearning4j.examples.dataexamples;
import sample.*;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.conditions.Condition;
import org.nd4j.linalg.io.ClassPathResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.deeplearning4j.nn.modelimport.keras.*;
import java.io.IOException;
public class hranalysis {
    private static Logger log = LoggerFactory.getLogger(hranalysis.class);
     public static void main(String [] args) throws IOException, UnsupportedKerasConfigurationException, InvalidKerasConfigurationException {
         sample.HRFX ob= new HRFX();
         HRFX.main(new String[]{});
         String mymodel = new ClassPathResource("hrmodel.hdf5").getFile().getPath();
         MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights("C:\\Users\\prajw\\hrmodel_json",mymodel);
         System.out.println(model.conf().toJson());
         int sala;
         int prev_evaluation=ob.get_prev_eval();
         if(prev_evaluation==0)
             sala =1;
         else
             sala=0;
         Integer satisfy_level=ob.get_satisfaction_level();
         Integer proj_num =ob.get_num_proj();
         Integer avg_mon_hrs=ob.get_avg_monthly_hours();
         Integer years =ob.get_num_years();
         int workaccident=ob.get_workaccident();
         int prev_prom=ob.get_prev_promotion();
         int dep =ob.get_dept();

         INDArray myarray = Nd4j.zeros(1,9);
         myarray.putScalar(0,0,sala);
         myarray.putScalar(0,1,prev_evaluation);
         myarray.putScalar(0,2,satisfy_level);
         myarray.putScalar(0,3,proj_num);
         myarray.putScalar(0,4,avg_mon_hrs);
         myarray.putScalar(0,5,years);
         myarray.putScalar(0,6,workaccident);
         myarray.putScalar(0,7,prev_prom);
         myarray.putScalar(0,8,dep);
         INDArray output = model.output(myarray);
         System.out.println(myarray);
         System.out.println(output);
     }
}



