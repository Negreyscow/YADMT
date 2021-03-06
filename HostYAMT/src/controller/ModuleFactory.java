/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import annotations.ClassifierModuleAnnotation;
import annotations.ElicitedBasesModuleAnnotation;
import annotations.EvaluationMetricModuleAnnotation;
import annotations.ModuleAnnotation;
import interfaces.mining.classify.ClassifierModuleInterface;
import interfaces.mining.classify.EvaluationMetricModuleInterface;
import interfaces.mining.classify.PartitionModuleInterface;
import interfaces.preprocess.ElicitedBasesModuleInterface;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import model.ModuleAvailable;
import moduledefault.classify.c45.C45ClassifierModule;
import moduledefault.classify.committee.CommitteeClassifierModule;
import moduledefault.classify.knn.KnnClassifierModule;
import moduledefault.classify.naivebayes.NaiveBayesClassifierModule;
import moduledefault.elicitedbases.postgresrafael.PostgresElicitedBases;
import moduledefault.elicitedbases.arff.ArffElicitedBases;
import moduledefault.evaluationmetric.accuracy.AccuracyEvaluationMetricModule;
import moduledefault.evaluationmetric.precision.PrecisionEvaluationMetricModule;
import moduledefault.evaluationmetric.recall.RecallEvaluationMetricModule;
import moduledefault.partition.holdout.HoldoutPartionModule;
import moduledefault.partition.kfold.KFoldPartionModule;
import moduledefault.partition.stratifiedholdout.StratifiedHoldoutPartionModule;
import moduledefault.partition.stratifiedkfold.StratifiedKFoldPartionModule;

/**
 *
 * @author evaristowb
 */
public class ModuleFactory {
//    private static ArrayList<ClassifierModuleInterface> arrayListClassifierModules = new ArrayList<ClassifierModuleInterface>();
//    private static ArrayList<ElicitedBasesInterface> arrayListElicitedBases = new ArrayList<ElicitedBasesInterface>();

    private static ArrayList<ModuleAvailable<ClassifierModuleInterface>> arrayListClassifierModules
            = new ArrayList<ModuleAvailable<ClassifierModuleInterface>>();
    private static ArrayList<ModuleAvailable<ElicitedBasesModuleInterface>> arrayListElicitedBasesModules
            = new ArrayList<ModuleAvailable<ElicitedBasesModuleInterface>>();
    private static ArrayList<ModuleAvailable<PartitionModuleInterface>> arrayListPartionModules
            = new ArrayList<ModuleAvailable<PartitionModuleInterface>>();
    private static ArrayList<ModuleAvailable<EvaluationMetricModuleInterface>> arrayListEvaluationMetrics
            = new ArrayList<ModuleAvailable<EvaluationMetricModuleInterface>>();

    private static URLClassLoader loader;

    static {
        try {
            File f = new File("Modules/");
            ArrayList<URL> urls = new ArrayList<URL>();
            if (f.listFiles() != null) {
                for (File file : f.listFiles()) {
                    if (file.getAbsolutePath().endsWith(".jar")) {
                        urls.add(file.toURI().toURL());
                    }
                }
            }

            //Default Modules
            ModuleAvailable<ElicitedBasesModuleInterface> moduleAvailablePostgres
                    = new ModuleAvailable<ElicitedBasesModuleInterface>();
            moduleAvailablePostgres.setName(PostgresElicitedBases.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailablePostgres.setConstructor((Constructor) PostgresElicitedBases.class.getConstructor());
            arrayListElicitedBasesModules.add(moduleAvailablePostgres);

            ModuleAvailable<ElicitedBasesModuleInterface> moduleAvailableArff
                    = new ModuleAvailable<ElicitedBasesModuleInterface>();
            moduleAvailableArff.setName(ArffElicitedBases.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableArff.setConstructor((Constructor) ArffElicitedBases.class.getConstructor());
            arrayListElicitedBasesModules.add(moduleAvailableArff);

            ModuleAvailable<PartitionModuleInterface> moduleAvailableHoldout
                    = new ModuleAvailable<PartitionModuleInterface>();
            moduleAvailableHoldout.setName(HoldoutPartionModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableHoldout.setConstructor((Constructor) HoldoutPartionModule.class.getConstructor());
            arrayListPartionModules.add(moduleAvailableHoldout);

            ModuleAvailable<PartitionModuleInterface> moduleAvailableKFold
                    = new ModuleAvailable<PartitionModuleInterface>();
            moduleAvailableKFold.setName(KFoldPartionModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableKFold.setConstructor((Constructor) KFoldPartionModule.class.getConstructor());
            arrayListPartionModules.add(moduleAvailableKFold);

            //Stratified Holdout
            ModuleAvailable<PartitionModuleInterface> moduleAvailableStratifiedHoldout
                    = new ModuleAvailable<PartitionModuleInterface>();
            moduleAvailableStratifiedHoldout.setName(StratifiedHoldoutPartionModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableStratifiedHoldout.setConstructor((Constructor) StratifiedHoldoutPartionModule.class.getConstructor());
            arrayListPartionModules.add(moduleAvailableStratifiedHoldout);

            //Stratified KFold
            ModuleAvailable<PartitionModuleInterface> moduleAvailableStratifiedKFold
                    = new ModuleAvailable<PartitionModuleInterface>();
            moduleAvailableStratifiedKFold.setName(StratifiedKFoldPartionModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableStratifiedKFold.setConstructor((Constructor) StratifiedKFoldPartionModule.class.getConstructor());
            arrayListPartionModules.add(moduleAvailableStratifiedKFold);

            ModuleAvailable<EvaluationMetricModuleInterface> moduleAvailableAccuracy
                    = new ModuleAvailable<EvaluationMetricModuleInterface>();
            moduleAvailableAccuracy.setName(AccuracyEvaluationMetricModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableAccuracy.setConstructor((Constructor) AccuracyEvaluationMetricModule.class.getConstructor());
            arrayListEvaluationMetrics.add(moduleAvailableAccuracy);

            ModuleAvailable<EvaluationMetricModuleInterface> moduleAvailablePrecision
                    = new ModuleAvailable<EvaluationMetricModuleInterface>();
            moduleAvailablePrecision.setName(PrecisionEvaluationMetricModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailablePrecision.setConstructor((Constructor) PrecisionEvaluationMetricModule.class.getConstructor());
            arrayListEvaluationMetrics.add(moduleAvailablePrecision);

            //Committee
            ModuleAvailable<ClassifierModuleInterface> moduleAvailableCommittee
                    = new ModuleAvailable<ClassifierModuleInterface>();
            moduleAvailableCommittee.setName(CommitteeClassifierModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableCommittee.setConstructor((Constructor) CommitteeClassifierModule.class.getConstructor());
            arrayListClassifierModules.add(moduleAvailableCommittee);

            ModuleAvailable<EvaluationMetricModuleInterface> moduleAvailableRecall
                    = new ModuleAvailable<EvaluationMetricModuleInterface>();
            moduleAvailableRecall.setName(RecallEvaluationMetricModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableRecall.setConstructor((Constructor) RecallEvaluationMetricModule.class.getConstructor());
            arrayListEvaluationMetrics.add(moduleAvailableRecall);

            ModuleAvailable<ClassifierModuleInterface> moduleAvailableC45
                    = new ModuleAvailable<ClassifierModuleInterface>();
            moduleAvailableC45.setName(C45ClassifierModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableC45.setConstructor((Constructor) C45ClassifierModule.class.getConstructor());
            arrayListClassifierModules.add(moduleAvailableC45);

            ModuleAvailable<ClassifierModuleInterface> moduleAvailableKnn
                    = new ModuleAvailable<ClassifierModuleInterface>();
            moduleAvailableKnn.setName(KnnClassifierModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableKnn.setConstructor((Constructor) KnnClassifierModule.class.getConstructor());
            arrayListClassifierModules.add(moduleAvailableKnn);

            ModuleAvailable<ClassifierModuleInterface> moduleAvailableNaiveBayes
                    = new ModuleAvailable<ClassifierModuleInterface>();
            moduleAvailableNaiveBayes.setName(NaiveBayesClassifierModule.class.getAnnotation(ModuleAnnotation.class).name());
            moduleAvailableNaiveBayes.setConstructor((Constructor) NaiveBayesClassifierModule.class.getConstructor());
            arrayListClassifierModules.add(moduleAvailableNaiveBayes);

            loader = new URLClassLoader(urls.toArray(new URL[0]));
            if (f.listFiles() != null) {
                for (File file : f.listFiles()) {
                    if (file.getAbsolutePath().endsWith(".jar")) {
                        ZipFile zipFile = null;
                        try {
                            zipFile = new ZipFile(file);
                            for (Enumeration e = zipFile.entries(); e.hasMoreElements();) {
                                ZipEntry zipEntry = (ZipEntry) e.nextElement();
                                if (zipEntry.isDirectory()) {
                                    continue;
                                }

                                String name = zipEntry.getName();
                                if (name.endsWith(".class")) {
                                    name = name.replaceAll("/", ".").replaceAll(".class", "");
                                    Class cl = Class.forName(name, true, loader);
                                    ModuleAnnotation module = (ModuleAnnotation) cl.getAnnotation(ModuleAnnotation.class);
                                    if (module != null) {
                                        String nameModule = module.name();

                                        //Elicitar qual modulo
                                        if (cl.getAnnotation(ClassifierModuleAnnotation.class) != null) {
                                            ModuleAvailable<ClassifierModuleInterface> moduleAvailable
                                                    = new ModuleAvailable<ClassifierModuleInterface>();
                                            moduleAvailable.setName(nameModule);
                                            moduleAvailable.setConstructor((Constructor<ClassifierModuleInterface>) cl.getConstructor());

                                            arrayListClassifierModules.add(moduleAvailable);
                                        }

                                        if (cl.getAnnotation(ElicitedBasesModuleAnnotation.class) != null) {
                                            ModuleAvailable<ElicitedBasesModuleInterface> moduleAvailable
                                                    = new ModuleAvailable<ElicitedBasesModuleInterface>();
                                            moduleAvailable.setName(nameModule);
                                            moduleAvailable.setConstructor((Constructor<ElicitedBasesModuleInterface>) cl.getConstructor());

                                            arrayListElicitedBasesModules.add(moduleAvailable);
                                        }

                                        if (cl.getAnnotation(EvaluationMetricModuleAnnotation.class) != null) {
                                            ModuleAvailable<EvaluationMetricModuleInterface> moduleAvailable
                                                    = new ModuleAvailable<EvaluationMetricModuleInterface>();
                                            moduleAvailable.setName(nameModule);
                                            moduleAvailable.setConstructor((Constructor<EvaluationMetricModuleInterface>) cl.getConstructor());

                                            arrayListEvaluationMetrics.add(moduleAvailable);
                                        }
                                    }
                                }
                            }
                        } catch (ZipException ex) {
                            Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            if (zipFile != null) {
                                try {
                                    zipFile.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                }
            }

//            URL urls[] = new URL[f.listFiles().length];
//            for (int i = 0; i < f.listFiles().length; i++){
//                urls[i] = new URL(f.listFiles()[i].toURI().toURL().toString());
//            }
//
//            URLClassLoader loader = new URLClassLoader(urls);
//            for (int i = 0; i < f.listFiles().length; i++) {
//                Class cl = Class.forName(f.listFiles()[i].getName().substring(0, f.listFiles()[i].getName().length() - 4).toLowerCase() + ".PainelAlgoritmo", true, loader);
//                Constructor c = cl.getConstructor(Class.forName("java.lang.String"));
//                DadosSobreAlgoritmo d = (DadosSobreAlgoritmo) cl.getAnnotations()[0];
//                algoritmosDisponiveis.add(new AlgoritmoDisponivel(d.Algoritmo(), c));
//            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ModuleFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ModuleFactory() {
    }

    /**
     * returns the arry list classifier modules
     *
     * @return
     */
    public static ArrayList<ModuleAvailable<ClassifierModuleInterface>> getArrayListClassifierModules() {
        return arrayListClassifierModules;
    }

    /**
     * returns the list elicited bases modules
     *
     * @return
     */
    public static ArrayList<ModuleAvailable<ElicitedBasesModuleInterface>> getArrayListElicitedBasesModules() {
        return arrayListElicitedBasesModules;
    }

    /**
     * returns the list partion modules
     *
     * @return
     */
    public static ArrayList<ModuleAvailable<PartitionModuleInterface>> getArrayListPartionModules() {
        return arrayListPartionModules;
    }

    /**
     * returns the list evaluation metrics
     *
     * @return
     */
    public static ArrayList<ModuleAvailable<EvaluationMetricModuleInterface>> getArrayListEvaluationMetrics() {
        return arrayListEvaluationMetrics;
    }

    /**
     * returns the loader
     *
     * @return
     */
    public static URLClassLoader getLoader() {
        return loader;
    }
}
