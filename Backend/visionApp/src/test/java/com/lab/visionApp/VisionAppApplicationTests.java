package com.lab.visionApp;

import java.util.List;
import java.util.stream.Collectors;

import com.google.cloud.vision.v1.EntityAnnotation;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assume.assumeThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class VisionAppApplicationTests {

	private static final String LABEL_IMAGE_URL = "/extractLabels?imageUrl=classpath:static/boston-terrier.jpg";

	private static final String TEXT_IMAGE_URL = "/extractText?imageUrl=classpath:static/stop-sign.jpg";

	@Autowired
	private MockMvc mockMvc;

	@BeforeClass
	public static void prepare() {
		assumeThat(
				"Vision Sample integration tests are disabled. Please use '-Dit.vision=true' "
						+ "to enable them.",
				System.getProperty("it.vision"), is("true"));
	}

	@Test
	public void testExtractTextFromImage() throws Exception {
		this.mockMvc.perform(get(TEXT_IMAGE_URL))
				.andDo((response) -> {
					ModelAndView result = response.getModelAndView();
					String textFromImage = ((String) result.getModelMap().get("text")).trim();
					assertThat(textFromImage).isEqualTo("STOP");
				});
	}

	@Test
	public void testClassifyImageLabels() throws Exception {
		this.mockMvc.perform(get(LABEL_IMAGE_URL))
				.andDo((response) -> {
					ModelAndView result = response.getModelAndView();
					List<EntityAnnotation> annotations = (List<EntityAnnotation>) result.getModelMap().get("annotations");

					List<String> annotationNames = annotations.stream()
							.map(annotation -> annotation.getDescription().toLowerCase().trim())
							.collect(Collectors.toList());

					assertThat(annotationNames).contains("boston terrier");
				});
	}

}
