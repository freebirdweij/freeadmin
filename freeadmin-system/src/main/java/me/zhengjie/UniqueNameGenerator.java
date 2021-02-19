package me.zhengjie;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.util.StringUtils;

public class UniqueNameGenerator extends AnnotationBeanNameGenerator {

	@Override

    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {

        //全限定类名
		if (definition instanceof AnnotatedBeanDefinition) {
			String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
			if (StringUtils.hasText(beanName)) {
				// Explicit bean name found.
				//beanName = definition.getBeanClassName();
				return beanName;
			}else {
				
				beanName = definition.getBeanClassName();
				return beanName;
			}
		}
		
		//return definition.getBeanClassName();
		// Fallback: generate a unique default bean name.
		return buildDefaultBeanName(definition, registry);


    }
}
