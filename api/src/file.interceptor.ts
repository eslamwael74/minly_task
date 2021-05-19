import { FileInterceptor } from '@nestjs/platform-express';
import { diskStorage } from 'multer';
import { extname } from 'path';
import { v4 as uuidv4 } from 'uuid';

export const uploadInterceptor = FileInterceptor('image', {
    storage: diskStorage({
        destination: './files',
        filename: (req, file, callback) => {
            const fileExtName = extname(file.originalname);
            callback(null, file.fieldname + "-" + uuidv4() + fileExtName);
        },
    }),
    fileFilter: (req, file, callback) => {
        if (!file.originalname.match(/\.(jpg|jpeg|png|gif)$/)) {
            return callback(new Error('Only image files are allowed!'), false);
        }
        callback(null, true);
    },
})