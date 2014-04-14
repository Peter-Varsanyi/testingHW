package basics;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

public class ClientInOrderTest {
    private static final String TEST_CONTENT = "testContent";
    private static final String TEST_CONTENT_FORMATTED = "<testContent/>";

    private Client underTest;

    private IMocksControl control;

    private Service service;
    private ContentFormat format;

    @Before
    public void setUp() {
        control = EasyMock.createControl();

        service = control.createMock(Service.class);
        format = control.createMock(ContentFormat.class);

        underTest = new Client(service, format);
    }

    @Test
    public void testGetContentFormattedShouldReturnContentProperly() {
        // GIVEN
        Long ident = 12L;
        control.checkOrder(true);

        service.connect();
        EasyMock.expect(service.getContent(ident)).andReturn(TEST_CONTENT);
        service.release();

        EasyMock.expect(format.format(TEST_CONTENT)).andReturn(TEST_CONTENT_FORMATTED).anyTimes();
        control.replay();
        // WHEN
        String result = underTest.getContentFormatted(ident);
        // THEN
        control.verify();
        Assert.assertEquals(TEST_CONTENT_FORMATTED, result);
    }
}
