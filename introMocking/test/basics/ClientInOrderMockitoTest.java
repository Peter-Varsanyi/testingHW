package basics;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ClientInOrderMockitoTest {
	private static final String TEST_CONTENT = "testContent";
	private static final String TEST_CONTENT_FORMATTED = "<testContent/>";

	private Client underTest;

	@Mock
	private Service service;
	@Mock
	private ContentFormat format;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		underTest = new Client(service, format);
	}

	@Test
	public void testGetContentFormattedShouldReturnContentProperly() {
		// GIVEN
		Long ident = 12L;
		BDDMockito.given(service.getContent(ident)).willReturn(TEST_CONTENT);
		BDDMockito.given(format.format(Mockito.anyString())).willReturn(
				TEST_CONTENT_FORMATTED);
		// WHEN
		String result = underTest.getContentFormatted(ident);
		// THEN
		InOrder inorder = BDDMockito.inOrder(service, format);
		inorder.verify(service).connect();
		inorder.verify(service).getContent(ident);
		inorder.verify(service).release();
		inorder.verify(format).format(TEST_CONTENT);
		Assert.assertEquals(TEST_CONTENT_FORMATTED, result);
	}
}
