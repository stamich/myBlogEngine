package pl.codecity.perun.file.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.codecity.perun.file.helper.Utils;

import java.io.IOException;
import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@Builder
@EqualsAndHashCode
public class BinaryFile implements Serializable {
    /**
     * File name
     */
    private String fileName;
    /**
     * Type of file
     */
    private String contentType;
    /**
     * Array containing binary representation of the file
     */
    private byte[] data;

    public byte[] getBytes() throws IOException {
        return Utils.toByteArray(this);
    }
}
