package com.example.quaresma.storage_armazenamento;

import com.orm.SugarRecord;

/**
 * Created by Quaresma on 12/10/2017.
 */

public class Imagem extends SugarRecord {

    private Long id;

    private byte[] pixels;

    public Imagem(Long id, byte[] pixels) {
        this.id = id;
        this.pixels = pixels;
    }

    public Imagem() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPixels() {
        return pixels;
    }

    public void setPixels(byte[] pixels) {
        this.pixels = pixels;
    }
}
