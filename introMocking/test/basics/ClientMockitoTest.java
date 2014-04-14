package basics;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ClientMockitoTest {

    private static final int TEST_CLIENT_IDENT = 2;
    private static final String TEST_SERVICE_NAME = "testservice";
    private static final String TEST_CONTENT = "testContent";
    private static final String TEST_DATE_FORMAT = "dateformat";
    private static final String TEST_TIMESTAMP = "2011/09/23:13:28.01";

    private Client underTest;

    @Mock
    private Service service;
    @Mock
    private ContentFormat format;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        underTest = new Client(service);
    }

    @Test
    public void testReleaseShouldReleaseService() {
        // GIVEN in setup
        // WHEN
        underTest.release();
        // THEN
        BDDMockito.verify(service).release();
    }

    @Test
    public void testGetServiceNameShouldReturnServiceName() {
        // GIVEN
        BDDMockito.given(service.getName()).willReturn(TEST_SERVICE_NAME);
        // WHEN
        String result = underTest.getServiceName();
        // THEN
        BDDMockito.verify(service).getName();
        Assert.assertEquals(TEST_SERVICE_NAME, result);
    }

    @Test
    public void testGetStartupShouldReturnStartupTime() {
        // GIVEN
        BDDMockito.given(service.getTimestamp(Mockito.anyString())).willReturn(TEST_TIMESTAMP);
        // WHEN
        String result = underTest.getStartup(TEST_DATE_FORMAT);
        // THEN
        BDDMockito.verify(service).getTimestamp(TEST_DATE_FORMAT);
        Assert.assertEquals(TEST_TIMESTAMP, result);
    }

    @Test(expected = RuntimeException.class)
    public void testGetStartupShouldThrowExceptionWhenServiceNotConnected() {
        // GIVEN
        BDDMockito.given(service.getTimestamp(Mockito.anyString())).willThrow(new RuntimeException());
        // WHEN
        underTest.getStartup(TEST_DATE_FORMAT);
        // THEN exception thrown
    }

    @Test
    public void testGetContentShouldReturnContentProperly() {
        // GIVEN
        Long ident = 12L;
        BDDMockito.given(service.getContent(Mockito.anyLong())).willReturn(TEST_CONTENT);
        // WHEN
        String result = underTest.getContent(ident);
        // THEN
        InOrder inorder = BDDMockito.inOrder(service);
        inorder.verify(service).connect();
        inorder.verify(service).getContent(ident);
        inorder.verify(service).release();

        Assert.assertEquals(TEST_CONTENT, result);
    }

    @Test
    public void testGetClientContentShouldReturnContentFormatted() {
        // GIVEN
        underTest = new Client(service, format);
        BDDMockito.given(format.format(BDDMockito.any(StringBuilder.class))).willReturn(TEST_CONTENT);
        // WHEN
        String result = underTest.getClientContentFormatted();
        // THEN
        ArgumentCaptor<StringBuilder> content = ArgumentCaptor.forClass(StringBuilder.class);
        BDDMockito.verify(format).format(content.capture());

        Assert.assertEquals(String.format("This is a client %s!", TEST_CLIENT_IDENT), content.getValue().toString());

        Assert.assertEquals(TEST_CONTENT, result);
    }
}
