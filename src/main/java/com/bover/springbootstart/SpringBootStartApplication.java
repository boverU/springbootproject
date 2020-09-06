package com.bover.springbootstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStartApplication.class, args);
	}

	@RestController
	class Resource {

		@RequestMapping(method = RequestMethod.GET)
		Message getMessage() {
			return new Message("Hello World!");
		}
	}


	class Message {
		public String getMessage() {
			return message;
		}

		@Override
		public String toString() {
			return "Message{" +
					"message='" + message + '\'' +
					'}';
		}

		private final String message;

		Message(String message) {
			this.message = message;
		}
	}
}