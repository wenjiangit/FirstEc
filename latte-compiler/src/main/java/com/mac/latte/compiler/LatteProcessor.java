package com.mac.latte.compiler;

import com.google.auto.service.AutoService;
import com.mac.latte.annotations.AppRegisterGenerator;
import com.mac.latte.annotations.EntryGenerator;
import com.mac.latte.annotations.PayEntryGenerator;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 *
 *
 * Created by douliu on 2017/8/21.
 */
@AutoService(Processor.class)
public class LatteProcessor extends AbstractProcessor {

    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        filer = env.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateEntryCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        generateAppRegisterCode(roundEnvironment);
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class.getCanonicalName());
        annotations.add(PayEntryGenerator.class.getCanonicalName());
        annotations.add(AppRegisterGenerator.class.getCanonicalName());
        return annotations;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void generateEntryCode(RoundEnvironment roundEnv) {
        final EntryVisitor visitor = new EntryVisitor(filer);
        scan(roundEnv, EntryGenerator.class, visitor);
    }

    private void generatePayEntryCode(RoundEnvironment roundEnv) {
        final PayEntryVisitor visitor = new PayEntryVisitor(filer);
        scan(roundEnv, PayEntryGenerator.class, visitor);
    }

    private void generateAppRegisterCode(RoundEnvironment roundEnv) {
        final AppRegisterVisitor visitor = new AppRegisterVisitor(filer);
        scan(roundEnv, AppRegisterGenerator.class, visitor);
    }


    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {

        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> annotationMirrors =
                    typeElement.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : annotationMirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                        : elementValues.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }


}
