package com.alver.fatefall.app.splash;

import javafx.beans.property.*;

public class BeanState<T> {
        private final StringProperty beanName = new SimpleStringProperty();
        private final ObjectProperty<T> bean = new SimpleObjectProperty<>();
        private final LongProperty startTime = new SimpleLongProperty();
        private final LongProperty endTime = new SimpleLongProperty();

        public String getBeanName() {
            return beanName.get();
        }

        public StringProperty beanNameProperty() {
            return beanName;
        }

        public void setBeanName(String beanName) {
            this.beanName.set(beanName);
        }

        public T getBean() {
            return bean.get();
        }

        public ObjectProperty<?> beanProperty() {
            return bean;
        }

        public void setBean(T bean) {
            this.bean.set(bean);
        }

        public long getStartTime() {
            return startTime.get();
        }

        public LongProperty startTimeProperty() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime.set(startTime);
        }

        public long getEndTime() {
            return endTime.get();
        }

        public LongProperty endTimeProperty() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime.set(endTime);
        }
    }