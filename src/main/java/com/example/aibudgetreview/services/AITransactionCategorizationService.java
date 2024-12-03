package com.example.aibudgetreview.services;

import com.example.aibudgetreview.dto.CategoryDTO;
import com.example.aibudgetreview.models.Category;
import com.example.aibudgetreview.models.Transaction;
import com.example.aibudgetreview.models.enums.CategoryType;
import com.example.aibudgetreview.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instances;

@Service
public class AITransactionCategorizationService {

    @Autowired
    private CategoryRepository categoryRepository;

    private Classifier classifier;
    private Instances trainingData;

    public AITransactionCategorizationService() throws Exception {
        this.classifier = new IBk();
        this.trainingData = loadTrainingData();
        trainModel();
    }

    private Instances loadTrainingData() throws Exception {
        return new Instances(new java.io.FileReader("transactions.arff"));
    }

    private void trainModel() throws Exception {
        // Trening modelu
        classifier.buildClassifier(trainingData);
    }

    public CategoryDTO categorizeTransaction(Transaction transaction) {
        try {
            DenseInstance instance = new DenseInstance(trainingData.numAttributes());
            instance.setValue(trainingData.attribute("description"), transaction.getDescription());

            var predictedClassIndex = classifier.classifyInstance(instance);

            String predictedCategoryName = trainingData.classAttribute().value((int) predictedClassIndex);

            Category category = categoryRepository.findByName(predictedCategoryName)
                    .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii o nazwie: " + predictedCategoryName));

            CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName(), category.getType());
            return categoryDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return new CategoryDTO(null, "Other", CategoryType.EXPENSE);
        }
    }
}
