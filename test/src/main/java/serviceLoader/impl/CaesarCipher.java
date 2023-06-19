package serviceLoader.impl;

import serviceLoader.Cipher;

import java.util.Optional;
import java.util.ServiceLoader;

public class CaesarCipher implements Cipher {
    public static ServiceLoader<Cipher> cipherLoader = ServiceLoader.load(Cipher.class);//初始化一个服务加载器
//  ServiceLoader.load(Cipher.class)创建一个服务加载器来加载实现给定服务接口的类

    /**
     * 查找所要的服务
     *
     * @param minStrength
     * @return
     */
    public static Cipher getCipher(int minStrength) {//使用迭代器
        for (Cipher cipher : cipherLoader) {
            if (cipher.strength() >= minStrength) return cipher;
        }
        return null;
    }

    public static Optional<Cipher> getCipher2(int minStrength) {//使用流
        return cipherLoader.stream()
//              stream()返回提供者描述符的一个流,从而可以采用懒加载方式加载所要的类的提供者
                .filter(descr -> descr.type() == serviceLoader.impl.CaesarCipher.class)
//              type()获得这个提供者的类型
                .findFirst()
//              findFirst()查找第一个可用的服务提供者
                .map(ServiceLoader.Provider::get);
//              get获得这个提供者的实例
    }

    @Override
    public byte[] encrypt(byte[] source, byte[] key) {
        var result = new byte[source.length];
        for (int i = 0; i < source.length; i++) {
            result[i] = (byte) (source[i] + key[0]);
        }
        return result;
    }

    @Override
    public byte[] decrypt(byte[] source, byte[] key) {
        return encrypt(source, new byte[]{(byte) -key[0]});
    }

    @Override
    public int strength() {
        return 1;
    }
}
