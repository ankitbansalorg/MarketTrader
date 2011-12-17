package com.sa.mt.options.repository;

import com.sa.mt.options.domain.ExpiryDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Arrays;
import java.util.List;

import static com.sa.mt.utils.DateUtils.getDate;
import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/testContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public class ExpiryDatesRepositoryTest {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ExpiryDatesRepository repository;

	@Before
	public void setUp() {
		mongoTemplate.dropCollection(ExpiryDate.class);
	}

	@Test
	public void shouldStoreExpiryDates() {
		ExpiryDate expiryDate = new ExpiryDate("Mar", "2008",
				getDate("27-mar-2008"));
		repository.save(Arrays.asList(expiryDate));
		List<ExpiryDate> expiryDates = repository.getAll();
		assertEquals(1, expiryDates.size());
		assertEquals(expiryDate, expiryDates.get(0));
	}
	
	@Test
	public void shouldNotStoreDuplicateExpiryDates() {
		ExpiryDate expiryDate1 = new ExpiryDate("Mar", "2008",
				getDate("27-mar-2008"));
		ExpiryDate expiryDate2 = new ExpiryDate("Mar", "2008",
				getDate("27-mar-2008"));

		repository.save(Arrays.asList(expiryDate1,expiryDate2));
		List<ExpiryDate> expiryDates = repository.getAll();
		assertEquals(1, expiryDates.size());
		assertEquals(expiryDate1, expiryDates.get(0));
	}

	@Test
	public void shouldFindNextExpiryDateForAGivenDate() {
		ExpiryDate expiryDate = new ExpiryDate(getDate("27-mar-2008"));
		repository.save(Arrays.asList(expiryDate));
		ExpiryDate result = repository.findExpiryDateFor(getDate("26-mar-2008"));
		assertEquals(expiryDate,result);
	}

	@Test
	public void shouldFindNextExpiryDateWhenDateIsGreaterThanThisMonthExpiryDate() {
		ExpiryDate expiryDate1 = new ExpiryDate(getDate("27-mar-2008"));
		ExpiryDate expiryDate2 = new ExpiryDate(getDate("27-apr-2008"));

		repository.save(Arrays.asList(expiryDate1, expiryDate2));
		ExpiryDate result = repository.findExpiryDateFor(getDate("27-mar-2008"));
		assertEquals(expiryDate2,result);
	}
}
