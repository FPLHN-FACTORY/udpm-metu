package udpm.hn.metu.infrastructure.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import udpm.hn.metu.entity.BusinessType;
import udpm.hn.metu.infrastructure.config.repository.DBGenBusinessTypeRepository;

import java.util.Date;
import java.util.Optional;


@Component
@Order(2)
@RequiredArgsConstructor
public class DBGenerator {

    private final DBGenBusinessTypeRepository businessTypeRepository;

    @Value("${db.generator.is-generated}")
    private String isGenerated;

    @PostConstruct
    public void init() {
        if ("true".equals(isGenerated)) generateData();
    }

    private void generateData() {

        generateBusinessType();

    }

    private void generateBusinessType() {
        Optional<BusinessType> businessTypeOptional = businessTypeRepository.findById("1");
        if(businessTypeOptional.isEmpty()){
            BusinessType businessType = new BusinessType();
            businessType.setId("1");
            businessType.setName("Individual");
            businessType.setCode("Individual");
            businessType.setDescription("Individual Account");
            businessType.setCreateBy("System");
            businessType.setUpdateBy("System");
            Date now = new Date();
            long nowTimeConvert = now.getTime();
            businessType.setCreateAt(nowTimeConvert / 1000);
            businessType.setCreateAt(nowTimeConvert / 1000);
            businessType.setDeleted(false);
            businessTypeRepository.save(businessType);
        }
    }

}
