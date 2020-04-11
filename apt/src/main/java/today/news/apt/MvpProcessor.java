package today.news.apt;

import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;


@AutoService(Processor.class)
@SupportedAnnotationTypes({"today.news.annotation.MvpEmptyViewFactory"})

public class MvpProcessor extends AbstractProcessor {

    public Filer mFiler;

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mFiler = processingEnv.getFiler();
        new MvpEmptyViewProcesor().process(roundEnvironment,this);
        return true;
    }
}
