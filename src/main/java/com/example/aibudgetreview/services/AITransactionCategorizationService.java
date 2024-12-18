package com.example.aibudgetreview.services;

import com.example.aibudgetreview.dto.CategoryDTO;
import com.example.aibudgetreview.dto.CategoryExpenseDTO;
import com.example.aibudgetreview.models.Category;
import com.example.aibudgetreview.models.Transaction;
import com.example.aibudgetreview.models.enums.CategoryType;
import com.example.aibudgetreview.repositories.CategoryRepository;
import com.example.aibudgetreview.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AITransactionCategorizationService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private Classifier classifier;
    private Instances trainingData;

    public AITransactionCategorizationService() throws Exception {
        this.classifier = new IBk();
        this.trainingData = loadTrainingData();
        trainModel();
    }

    private Instances loadTrainingData() throws Exception {
        return new Instances(new java.io.FileReader("data/learning.arff"));
    }

    public CategoryDTO categorizeTransaction(Transaction transaction) {
        try {
            DenseInstance instance = new DenseInstance(trainingData.numAttributes());
            instance.setValue(trainingData.attribute("description"), transaction.getDescription());

            var predictedClassIndex = classifier.classifyInstance(instance);

            String predictedCategoryName = trainingData.classAttribute().value((int) predictedClassIndex);

            Category category = categoryRepository.findByName(predictedCategoryName)
                    .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii o nazwie: " + predictedCategoryName));

            return new CategoryDTO(category.getId(), category.getName(), category.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return new CategoryDTO(null, "Other", CategoryType.EXPENSE);
        }
    }
    public void trainModel() throws Exception {
        trainingData = loadTrainingData();
        classifier.buildClassifier(trainingData);
        System.out.println("Model retrained successfully.");
    }

}
