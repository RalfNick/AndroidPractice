package wang.ralf.aidlserver;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name Test
 * @email -
 * @date 2019/05/20 15:43
 **/
public class Test {

    public interface IMyService extends android.os.IInterface {
        /**
         * Local-side IPC implementation stub class.
         */
        public static abstract class Stub extends android.os.Binder implements wang.ralf.aidlserver.IMyService {
            private static final java.lang.String DESCRIPTOR = "wang.ralf.aidlserver.IMyService";

            /**
             * Construct the stub at attach it to the interface.
             */
            public Stub() {
                this.attachInterface(this, DESCRIPTOR);
            }

            /**
             * Cast an IBinder object into an wang.ralf.aidlserver.IMyService interface,
             * generating a proxy if needed.
             */
            public static wang.ralf.aidlserver.IMyService asInterface(android.os.IBinder obj) {
                if ((obj == null)) {
                    return null;
                }
                android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
                if (((iin != null) && (iin instanceof wang.ralf.aidlserver.IMyService))) {
                    return ((wang.ralf.aidlserver.IMyService) iin);
                }
                return new wang.ralf.aidlserver.IMyService.Stub.Proxy(obj);
            }

            @Override
            public android.os.IBinder asBinder() {
                return this;
            }

            @Override
            public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
                java.lang.String descriptor = DESCRIPTOR;
                switch (code) {
                    case INTERFACE_TRANSACTION: {
                        reply.writeString(descriptor);
                        return true;
                    }
                    case TRANSACTION_add: {
                        data.enforceInterface(descriptor);
                        int _arg0;
                        _arg0 = data.readInt();
                        int _arg1;
                        _arg1 = data.readInt();
                        this.add(_arg0, _arg1);
                        reply.writeNoException();
                        return true;
                    }
                    case TRANSACTION_getStudent: {
                        data.enforceInterface(descriptor);
                        java.util.List<wang.ralf.aidlserver.Student> _result = this.getStudent();
                        reply.writeNoException();
                        reply.writeTypedList(_result);
                        return true;
                    }
                    case TRANSACTION_addStudent: {
                        data.enforceInterface(descriptor);
                        wang.ralf.aidlserver.Student _arg0;
                        if ((0 != data.readInt())) {
                            _arg0 = wang.ralf.aidlserver.Student.CREATOR.createFromParcel(data);
                        } else {
                            _arg0 = null;
                        }
                        this.addStudent(_arg0);
                        reply.writeNoException();
                        return true;
                    }
                    default: {
                        return super.onTransact(code, data, reply, flags);
                    }
                }
            }

            private static class Proxy implements wang.ralf.aidlserver.IMyService {
                private android.os.IBinder mRemote;

                Proxy(android.os.IBinder remote) {
                    mRemote = remote;
                }

                @Override
                public android.os.IBinder asBinder() {
                    return mRemote;
                }

                public java.lang.String getInterfaceDescriptor() {
                    return DESCRIPTOR;
                }

                @Override
                public void add(int a, int b) throws android.os.RemoteException {
                    android.os.Parcel _data = android.os.Parcel.obtain();
                    android.os.Parcel _reply = android.os.Parcel.obtain();
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeInt(a);
                        _data.writeInt(b);
                        mRemote.transact(Stub.TRANSACTION_add, _data, _reply, 0);
                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }
                }

                @Override
                public java.util.List<wang.ralf.aidlserver.Student> getStudent() throws android.os.RemoteException {
                    android.os.Parcel _data = android.os.Parcel.obtain();
                    android.os.Parcel _reply = android.os.Parcel.obtain();
                    java.util.List<wang.ralf.aidlserver.Student> _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        mRemote.transact(Stub.TRANSACTION_getStudent, _data, _reply, 0);
                        _reply.readException();
                        _result = _reply.createTypedArrayList(wang.ralf.aidlserver.Student.CREATOR);
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }
                    return _result;
                }

                @Override
                public void addStudent(wang.ralf.aidlserver.Student student) throws android.os.RemoteException {
                    android.os.Parcel _data = android.os.Parcel.obtain();
                    android.os.Parcel _reply = android.os.Parcel.obtain();
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        if ((student != null)) {
                            _data.writeInt(1);
                            student.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        mRemote.transact(Stub.TRANSACTION_addStudent, _data, _reply, 0);
                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }
                }
            }

            static final int TRANSACTION_add = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
            static final int TRANSACTION_getStudent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
            static final int TRANSACTION_addStudent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
        }

        public void add(int a, int b) throws android.os.RemoteException;

        public java.util.List<wang.ralf.aidlserver.Student> getStudent() throws android.os.RemoteException;

        public void addStudent(wang.ralf.aidlserver.Student student) throws android.os.RemoteException;
    }

}
