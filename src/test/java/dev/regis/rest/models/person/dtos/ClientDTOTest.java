package dev.regis.rest.models.person.dtos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dev.regis.rest.models.production.ExpeditionPlan;

public class ClientDTOTest {

    @Test
    public void testGetId() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(1L);
        assertEquals(1L, clientDTO.getId());
    }

    @Test
    public void testGetName() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John Doe");
        assertEquals("John Doe", clientDTO.getName());
    }

    @Test
    public void testGetListExpeditionPlans() {
        List<ExpeditionPlan> plans = new ArrayList<>();
        // Adicione os planos à lista plans...

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setListExpeditionPlans(plans);

        assertEquals(plans, clientDTO.getListExpeditionPlans());
    }

    @Test
    public void testEquals() {
        ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setId(1L);
        clientDTO1.setName("John Doe");

        ClientDTO clientDTO2 = new ClientDTO();
        clientDTO2.setId(1L);
        clientDTO2.setName("John Doe");

        assertTrue(clientDTO1.equals(clientDTO2));
    }

    @Test
    public void testNotEquals() {
        ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setId(1L);
        clientDTO1.setName("John Doe");

        ClientDTO clientDTO2 = new ClientDTO();
        clientDTO2.setId(2L);
        clientDTO2.setName("Jane Doe");

        assertFalse(clientDTO1.equals(clientDTO2));
    }

    @Test
    public void testHashCode() {
        ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setId(1L);
        clientDTO1.setName("John Doe");

        ClientDTO clientDTO2 = new ClientDTO();
        clientDTO2.setId(1L);
        clientDTO2.setName("John Doe");

        assertEquals(clientDTO1.hashCode(), clientDTO2.hashCode());
    }

    @Test
    public void testToString() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(1L);
        clientDTO.setName("John Doe");

        String expectedString = "ClientDTO(id=1, name=John Doe, listExpeditionPlans=null)";
        assertEquals(expectedString, clientDTO.toString());
    }

    @Test
    public void testCanEqual() {
        ClientDTO clientDTO = new ClientDTO();

        assertTrue(clientDTO.canEqual(new ClientDTO()));
        assertFalse(clientDTO.canEqual(new Object()));
    }
}
