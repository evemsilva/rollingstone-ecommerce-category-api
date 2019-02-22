package com.rollingstone.events;

import com.rollingstone.spring.model.Category;
import org.springframework.context.ApplicationEvent;

public class CategoryEvent
		extends ApplicationEvent {

    private String   eventType;
    private Category category;

    public String getEventType() {
	return eventType;
    }

    public void setEventType(String eventType) {
	this.eventType = eventType;
    }

    public Category getCategory() {
	return category;
    }

    public void setCategory(Category category) {
	this.category = category;
    }

    public CategoryEvent(String eventType, Category category) {
	super(category);
	this.eventType = eventType;
	this.category = category;
    }

    @Override
    public String toString() {
	return "Category Event [eventType=" + eventType + ", category=" + category + "]";
    }

}
