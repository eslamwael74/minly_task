import { Controller, Get, Post, UploadedFile, UseInterceptors } from '@nestjs/common';
import { FileInterceptor } from '@nestjs/platform-express';
import { Injectable } from '@nestjs/common';
import { diskStorage } from 'multer';
import { extname } from 'path';
import { uuid } from 'uuidv4';
import * as fs from 'fs';

@Injectable()
export class AppService {
  getHello(): string {
    return 'Hello World!';
  }


  async uploadedFile({originalname,filename}) {
    const response = {
      originalname,
      filename,
    };
    return response;
  }

  getAllImages() {
    return fs.readdirSync('./files');
  }

}
